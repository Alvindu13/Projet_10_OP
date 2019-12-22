/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.persistance.dao.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * The type Book svc.
 */
@Service
public class BookSvcImpl implements BookSvc {

    private static final Logger logger = LoggerFactory.getLogger(BookSvcImpl.class);
    Deque<String> reserveQueue;
    Map<String, Deque> allWaitingQueues = new HashMap<>();



    /**
     * The Repo.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * The App user repository.
     */
    @Autowired
    AppUserRepository appUserRepository;


    /**
     * This method enable to
     * @param book
     * @param username of user connected
     */
    public void addQueueToReserve(Book book, String username){

        List<Book> books = bookRepository.findAllByTitle(book.getTitle());

        // On definie les paramètres de la file d'attente
        Long sizeQueueL = (long) books.size();
        Integer sizeQueueI = sizeQueueL == null ? null : Math.toIntExact(sizeQueueL);

        // On créé une file d'attente de taille maxi sizeQueueI
        Deque reserveQueueTempForThisBook = new ArrayDeque<>(sizeQueueI);
        reserveQueueTempForThisBook.add(username);

        Map<String, Deque> map = new HashMap<>();
        map.put(username, reserveQueueTempForThisBook);

        reserveQueue = searchQueueToBook(map, book, sizeQueueI);




        linkBookToQueue(book, reserveQueueTempForThisBook);









        //allWaitingQueues.computeIfAbsent(book.getTitle(), k -> new ArrayDeque()).add(reserveQueue);

        //allWaitingQueues.put(book.getTitle(), borrowerQueue);

    }


    /**
     * This method enable to
     * @param book
     */
    public void linkBookToQueue(Book book, Deque deque){

        allWaitingQueues.computeIfAbsent(book.getTitle(), k -> new ArrayDeque()).add(reserveQueue);
    }


    /**
     * This method enable to
     * @param book
     */
    public Deque searchQueueToBook(Map<String, Deque> map, Book book, Integer sizeQueueI) {
        for (Map.Entry<String, Deque> entry : map.entrySet()) {
            if (entry.getKey().equals(book.getTitle())) {
                return entry.getValue();
            }
        }
        return new ArrayDeque<>(sizeQueueI);
    }





    public void StockQueue(){

    }



    /**
     * This method enable to reserve a book
     * Cas où le livre n'est pas dispo (il faut le réserver)
     * The book can be not available for 2 reasons (quantity = 0 OR it is borrow)
     *
     * @param book     selected book
     * @param username of user connected
     */
    public void reserve(Book book, String username){

        // On récupère tous les exemplaires de ce livre "titre" dans une liste
        List<Book> books = bookRepository.findAllByTitle(book.getTitle());

        // Création d'un stream book
        Supplier<Stream<Book>> streamSupplier
                = () -> books.stream();
        streamSupplier.get().forEach(System.out::println);

       /* // Renvoie le nb de livres qui correspond au titre et qui sont dispos
        Long nbBooksAvalaible = streamSupplier.get()
                .filter((p) -> book.equals(p.getTitle()) && p.getAvailable())
                .count();*/

        // traitement stream qui retourne le premier livre qui est dispo avec le titre demandé
        Optional<Book> bookCorrespond = streamSupplier.get()
                .filter((b) -> book.getTitle().equals(b.getTitle()) && b.getAvailable()).findFirst();

        // On ajoute l'user à la file d'attente en première position
        //addQueueToReserve(username, nbBooks);

        // On set le livre et le save
        if(bookCorrespond.isPresent()){
            bookCorrespond.get().setBorrower(appUserRepository.findByUsername(reserveQueue.pop()));
            bookCorrespond.get().setBorrowDate(LocalDate.now());
            bookRepository.save(bookCorrespond.get());
        } else {
            System.out.println("not present book");
        }

        System.err.println("Voici la queue = " + reserveQueue);

    }



    /**
     * Cas où au moins un livre du titre est disponible
     *
     * @param book book selected to extend it reservation
     */
    public void borrow(Book book, String username) {
        book.setBorrower(appUserRepository.findByUsername(reserveQueue.pop()));
        book.setBorrowDate(LocalDate.now());
        book.setAvailable(false);
        bookRepository.save(book);
    }


    /**
     * Enable to extend one reservation of 4 weeks
     *
     * @param book book selected to extend it reservation
     */
    public void bookSystem(Book book, String username) {
        if(book.getAvailable()){
            borrow(book, username);
        } else /*reserve*/{
            addQueueToReserve(book, username);
            reserve(book, username);
        }
    }


    /**
     * Enable to extend one reservation of 4 weeks
     *
     * @param book book selected to extend it reservation
     */
    public void extend(Book book) {

        LocalDate ldt = book.getBorrowDate();

        // add 4 weeks of delay to reservation book
        Objects.requireNonNull(book.getIsProlongation(), "prolongation parameter is null");
        if (!book.getIsProlongation()) {
            book.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            book.setIsProlongation(true);
            bookRepository.save(book);
        } else {
            logger.info("reservation has already extend ! ");

        }
    }

    /**
     * Search books by keyword.
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Book> findAllByNameDistinctContains(String keyword) {
        return bookRepository.findDistinctByTitleContains(keyword);
    }


    /**
     * Search books by title.
     *
     * @param title
     * @return
     */
    @Override
    public List<Book> findAllByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }


    /**
     * Find all books of db.
     *
     * @return
     */
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Count books in db.
     *
     * @param title
     * @return
     */
    @Override
    public Long countByName(String title) {
        return bookRepository.countByTitle(title);
    }

    /**
     * Find books by parameters.
     *
     * @param username of user connected
     * @return
     */
    @Override
    public List<Book> findAllByBorrowerUsername(String username) {
        return bookRepository.findAllByBorrowerUsername(username);
    }

    /**
     * Find book by book_id.
     *
     * @param bookId
     * @return
     */
    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    /**
     * Save book.
     *
     * @param newBook the new book
     * @return
     */
    @Override
    public void save(Book newBook, String username) {
        newBook.setIsProlongation(false);
        newBook.setAvailable(true);
        newBook.setBorrower(appUserRepository.findByUsername(username));
        bookRepository.save(newBook);
    }

    /**
     * Stop book reserve.
     *
     * @param book
     */
    @Override
    public void stopReserve(Book book) {
        book.setAvailable(true);
        book.setBorrower(null);
        book.setIsProlongation(false);
        //book.setReturnDate(currentDate);
    }

}


