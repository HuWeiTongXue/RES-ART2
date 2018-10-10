package com.rotai.res_art2.chapter2.aidl;
import com.rotai.res_art2.chapter2.aidl.Book;
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}