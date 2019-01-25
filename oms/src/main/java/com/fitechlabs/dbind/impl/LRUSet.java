// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LRUSet.java

package com.fitechlabs.dbind.impl;

import java.util.HashMap;
import java.util.Map;

public class LRUSet
{
    private static class Node
    {

        private void remove()
        {
            prev.next = next;
            next.prev = prev;
        }

        private void addAfter(Node predecessor)
        {
            next = predecessor.next;
            prev = next.prev;
            next.prev = prev.next = this;
        }

        public String toString()
        {
            return "N(" + item + ")";
        }

        private Node prev;
        private Node next;
        private Object item;








        private Node(Object item)
        {
            this.item = item;
        }

    }


    public LRUSet()
    {
        map = new HashMap();
        head = new Node(null);
        head.prev = head.next = head;
    }

    public void add(Object item)
    {
        Node node = (Node)map.get(item);
        if(node != null)
        {
            node.remove();
            node.addAfter(head);
        } else
        {
            node = new Node(item);
            map.put(item, node);
            node.addAfter(head);
        }
    }

    public void remove(Object item)
    {
        Node node = (Node)map.remove(item);
        if(node != null)
            node.remove();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public Object removeOldest()
    {
        Node node = head.prev;
        if(node == head)
        {
            return null;
        } else
        {
            map.remove(node.item);
            node.remove();
            return node.item;
        }
    }

    public String toString()
    {
        StringBuffer b = new StringBuffer("{");
        for(Node n = head.next; n != head; n = n.next)
        {
            b.append(String.valueOf(n.item));
            if(n.next != head)
                b.append(",");
        }

        b.append("}");
        return b.toString();
    }

    public int getSize()
    {
        return map.size();
    }

    public static LRUSet synchronizedLRUSet(LRUSet set)
    {
        return new LRUSet() {

            public synchronized void add(Object item)
            {
                add(item);
            }

            public synchronized void remove(Object item)
            {
                remove(item);
            }

            public synchronized boolean isEmpty()
            {
                return isEmpty();
            }

            public synchronized Object removeOldest()
            {
                return removeOldest();
            }

            public synchronized String toString()
            {
                return toString();
            }

        }
;
    }

    private Map map;
    private Node head;
}
