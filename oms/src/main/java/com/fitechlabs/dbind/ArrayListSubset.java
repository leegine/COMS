// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrayListSubset.java

package com.fitechlabs.dbind;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind:
//            ListSubset

public class ArrayListSubset
    implements ListSubset, Serializable
{

    public static ArrayListSubset fromSubList(List subset, int fromIndex, int totalSize)
    {
        int n = totalSize;
        int toIndex = fromIndex + subset.size();
        return new ArrayListSubset(subset, fromIndex, toIndex, totalSize);
    }

    public static ArrayListSubset fromEntireList(List entireList, int fromIndex, int toIndex)
    {
        int n = entireList.size();
        int i = fromIndex;
        int j = toIndex;
        i = Math.min(Math.max(i, 0), n);
        j = Math.min(Math.max(j, i), n);
        return new ArrayListSubset(entireList.subList(i, j), i, j, entireList.size());
    }

    private ArrayListSubset(List subset, int fromIndex, int toIndex, int totalSize)
    {
        _subList = subset;
        _fromIndex = fromIndex;
        _toIndex = toIndex;
        _totalSize = totalSize;
    }

    public String toString()
    {
        return "(" + _fromIndex + "-" + _toIndex + "/" + _totalSize + "=" + _subList + ")";
    }

    public int fromIndex()
    {
        return _fromIndex;
    }

    public int toIndex()
    {
        return _toIndex;
    }

    public int totalSize()
    {
        return _totalSize;
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
        return _subList.containsAll(c);
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

    private List _subList;
    private int _fromIndex;
    private int _toIndex;
    private int _totalSize;
}
