// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrayListPage.java

package com.fitechlabs.dbind;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind:
//            ListPage, ListSubset

public class ArrayListPage
    implements ListPage, Serializable
{

    public ArrayListPage(List entireList, int pageSize, int pageNumber)
    {
        int n = entireList.size();
        int i = pageSize * pageNumber;
        int j = i + pageSize;
        j = Math.min(j, n);
        i = Math.min(i, j);
        setSubList(entireList.subList(i, j));
        _pageSize = pageSize;
        _pageNumber = pageNumber;
        _totalSize = n;
        _totalPages = ((n + pageSize) - 1) / pageSize;
    }

    public ArrayListPage(List relevantSubset, int pageSize, int pageNumber, int totalSize)
    {
        setSubList(relevantSubset);
        _pageSize = pageSize;
        _pageNumber = pageNumber;
        _totalSize = totalSize;
        _totalPages = ((totalSize + pageSize) - 1) / pageSize;
    }

    public ArrayListPage(ListSubset subset, int pageSize)
    {
        setSubList(subset);
        _pageSize = pageSize;
        _pageNumber = subset.fromIndex() / pageSize();
        _totalSize = subset.totalSize();
        _totalPages = ((_totalSize + pageSize) - 1) / pageSize;
    }

    private void setSubList(List subList)
    {
        _subList = Collections.unmodifiableList(subList);
    }

    public String toString()
    {
        return "[" + _pageNumber + "(" + _pageSize + ")" + "/" + _totalPages + ": " + _subList + "]";
    }

    public int pageSize()
    {
        return _pageSize;
    }

    public int pageNumber()
    {
        return _pageNumber;
    }

    public int totalSize()
    {
        return _totalSize;
    }

    public int totalPages()
    {
        return _totalPages;
    }

    public int size()
    {
        return _subList.size();
    }

    public boolean isEmpty()
    {
        return _subList.isEmpty();
    }

    public boolean contains(Object o)
    {
        return _subList.contains(o);
    }

    public Iterator iterator()
    {
        return _subList.iterator();
    }

    public Object[] toArray()
    {
        return _subList.toArray();
    }

    public Object[] toArray(Object a[])
    {
        return _subList.toArray(a);
    }

    public boolean add(Object o)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean containsAll(Collection c)
    {
        throw new UnsupportedOperationException("Method containsAll() not yet implemented.");
    }

    public boolean addAll(Collection c)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean addAll(int index, Collection c)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean removeAll(Collection c)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean retainAll(Collection c)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public void clear()
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public boolean equals(Object o)
    {
        return _subList.equals(o);
    }

    public Object get(int index)
    {
        return _subList.get(index);
    }

    public Object set(int index, Object element)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public void add(int index, Object element)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public Object remove(int index)
    {
        throw new UnsupportedOperationException("ArrayListPage is not mutable");
    }

    public int indexOf(Object o)
    {
        return _subList.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
        return _subList.lastIndexOf(o);
    }

    public ListIterator listIterator()
    {
        return _subList.listIterator();
    }

    public ListIterator listIterator(int index)
    {
        return _subList.listIterator(index);
    }

    public List subList(int fromIndex, int toIndex)
    {
        return _subList.subList(fromIndex, toIndex);
    }

    private int _pageSize;
    private int _pageNumber;
    private int _totalSize;
    private int _totalPages;
    private List _subList;
}
