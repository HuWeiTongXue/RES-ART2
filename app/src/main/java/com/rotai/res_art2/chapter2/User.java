package com.rotai.res_art2.chapter2;

import android.os.Parcel;
import android.os.Parcelable;

import com.rotai.res_art2.chapter2.aidl.Book;

import java.io.Serializable;

/**
 *  实现对象序列化
 *  Serializable：只需要让类实现 Serializable 接口；
 *  Parcelable：需要让类实现Parcelable 接口，实现序列化、反序列化和功能描述。
 */
public class User implements Serializable,Parcelable{
    private static final long serialVersionUID=4546486451230230L;

    public int userId;
    public String userName;
    public boolean isMale;
    public Book book;

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected User(Parcel in) {
        userName = in.readString();
        userId = in.readInt();
        isMale = in.readByte() != 0;
        book=in.readParcelable(Thread.currentThread().getContextClassLoader());
        //由于book是另一个可序列化对象，所以它的反序列化需要传递当前线程的上下文类加载器
    }

    /**
     * Parcelable 反序列化通过CREATOR来完成，表明如何创建序列化对象和数组
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    /**
     * Parcelable 内容描述
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *  Parcelable 序列化过程
     */
    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(userName);
        out.writeInt(userId);
        out.writeByte((byte) (isMale ? 1 : 0));
        out.writeParcelable(book,0);
    }

    @Override
    public String toString() {
        return String.format(
                "User:{userId:%s, userName:%s, isMale:%s}, with child:{%s}",
                userId, userName, isMale, book);
    }
}
