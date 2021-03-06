package com.skinnybonesarv.model;

import com.skinnybonesarv.util.CSVWritable;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Convention in Objects:
 *
 * Non-public and non-static variables' prefix -> m
 *      EX: private int mInteger;
 * Static variables' prefix -> s
 *      EX: private static int sInteger;
 *
 * Final static variables -> UPPERCASE_WORDS
 *      EX: private static final int INTEGER;
 * Other variables -> normal
 *      EX: public int integer;
 *
 * @author SkinnyBonesArv
 */
public abstract class Person implements CSVWritable {

    private String mLastName;
    private String mFirstName;
    private LocalDate mDateOfBirth;
    private int mID;
    private Sex mSex;

    public Person(String lastName, String firstName, LocalDate dateOfBirth, Sex sex) {
        setLastName(lastName);
        setFirstName(firstName);
        setDateOfBirth(dateOfBirth);
        setSex(sex);
    }

    // Constructor for Binary Search
    public Person(int id) {
        mID = id;
    }

    public enum Sex {
        MALE, FEMALE, OTHER
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1, lastName.length()).toLowerCase();
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1, firstName.length()).toLowerCase();
    }

    public LocalDate getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(mDateOfBirth, LocalDate.now()).getYears();
    }

    public int getID() {
        return mID;
    }

    protected void setID(int id) {
        mID = id;
    }

    public Sex getSex() {
        return mSex;
    }

    public void setSex(Sex sex) {
        mSex = sex;
    }

    @Override
    public Map<String, Supplier> getCSVTelemetry() {
        return null;
    }

    protected abstract void writeToFile();

    protected String toFile() {
        return String.format("%s, %s, %s, %s, ", getLastName(), getFirstName(), getDateOfBirth(), getSex());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s %-10s ", getLastName(), getFirstName(), getDateOfBirth(), getSex());
    }

}
