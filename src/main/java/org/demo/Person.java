package org.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person
{
    @Id
    @GeneratedValue
    int id;
    String name;
    String address;
    int age;

    public Person()
    {
        
    }
    public Person(String name, String address, int age)
    {
        super();
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String toString()
    {
        return id + " " + name + " " + address + " " + age;
    }
}
