// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LRUCache.java

package com.fitechlabs.dbind.impl;

import java.util.HashMap;
import java.util.Map;

public class LRUCache
{
    private class Node
    {

        private Object key;
        private Object item;
        private Node prev;
        private Node next;







        private Node()
        {
            prev = next = this;
        }

        private Node(Object key, Object item)
        {
            this.key = key;
            this.item = item;
            prev = next = null;
        }


    }


    public LRUCache(int maxcount)
    {
        hitNum = 0;
        dbg = true;
        map = new HashMap(maxcount);
        head = new Node();
        this.maxcount = maxcount;
        count = 0;
    }

    public Object get(Object key)
    {
        LRUCache lrucache = this;
        JVM INSTR monitorenter ;
        Node node;
        node = (Node)map.get(key);
        if(node == null)
            break MISSING_BLOCK_LABEL_51;
        if(dbg)
            hitNum++;
        touch(node);
        return node.item;
        null;
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Object getOrPut(Object key, Object object)
    {
        LRUCache lrucache = this;
        JVM INSTR monitorenter ;
        Node node;
        node = (Node)map.get(key);
        if(node == null)
            break MISSING_BLOCK_LABEL_38;
        touch(node);
        return node.item;
        if(count >= maxcount)
            remove(head.prev.key);
        addHead(node = new Node(key, object));
        map.put(node.key, node);
        object;
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Object getOrPut(Object key, Object object, Object removed[])
    {
        LRUCache lrucache = this;
        JVM INSTR monitorenter ;
        Node node;
        removed[0] = null;
        node = (Node)map.get(key);
        if(node == null)
            break MISSING_BLOCK_LABEL_44;
        touch(node);
        return node.item;
        if(count >= maxcount)
            removed[0] = remove(head.prev.key);
        addHead(node = new Node(key, object));
        map.put(node.key, node);
        object;
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Object remove(Object key)
    {
        LRUCache lrucache = this;
        JVM INSTR monitorenter ;
        Node node = (Node)map.remove(key);
        if(node == null)
            return null;
        removeNode(node);
        node.item;
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
        synchronized(this)
        {
            map.clear();
            head.next = head.prev = head;
            count = 0;
        }
    }

    public int getSize()
    {
        return count;
    }

    public int getCapacity()
    {
        return maxcount;
    }

    public boolean isValid()
    {
        return true;
    }

    private void addHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node.next.prev = node;
        count++;
    }

    private Node removeTail()
    {
        return removeNode(head.prev);
    }

    private Node removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        count--;
        return node;
    }

    private void touch(Node node)
    {
        addHead(removeNode(node));
    }

    private Map map;
    private Node head;
    private int maxcount;
    private int count;
    int hitNum;
    private boolean dbg;
}
