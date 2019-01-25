/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TestQuoteDataHolderクラス(WEB3TestQuoteDataHolder.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/08 山田　卓司(FLJ) 新規作成
 */
package webbroker3.intellioms.quote.adaptor.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;




class WEB3TestQuoteDataHolder
{
    
    private long seqNo;
    private LinkedList queue;
    private Random random;

    WEB3TestQuoteDataHolder()
    {
        seqNo = 1000000000;
        queue = new LinkedList();
        random = new Random(System.currentTimeMillis());
    }
    
    synchronized void load(String fileName)
    {
        
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            
            System.out.println("Quote data file found. file=" + fileName);
            
            String source = null;
            while ((source = reader.readLine()) != null)
            {
                if (source.length() > 0 && !source.startsWith("#") && source.length()==214)
                {
                    source = source.substring(35);
                    queue.addLast(source);
                }
                else if (source.length() > 0 && !source.startsWith("#"))
                {
                    queue.addLast(source);
                }
            }
            
        } catch (FileNotFoundException fnfe)
        {
            System.out.println("Quote data file not found.");
            fnfe.printStackTrace();
        } catch (IOException ioe)
        {
            System.out.println("Exception occured while parsing quote data file.");
            ioe.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException ioe)
                {
                    System.out.println("Exception occured while closing.");
                    ioe.printStackTrace();
                }
            }
        }
    }

    synchronized void push(String quoteData)
    {
        queue.addLast(quoteData);
    }

    synchronized byte[] pop(int l_intCount)
    {

        StringBuffer sb = new StringBuffer();

        // シーケンスNO
        sb.append(getSeqNo());

        // 時価データ
        int size = l_intCount;
        int recordCount = 0;
        for (int i = 0; i < size; i++)
        {
            if (!queue.isEmpty())
            {
                String quoteData = (String) queue.removeFirst();
                sb.append(quoteData).append("\n");
                recordCount++;
            } else
            {
                break;
            }
        }

        // レコード数
        if (recordCount == 10)
        {
            sb.insert(10, recordCount);
        } else
        {
            sb.insert(10, " ");
            sb.insert(11, recordCount);
        }
        
        return sb.toString().getBytes();
        
    }

    synchronized byte[] pop()
    {
        return pop(random.nextInt(10) + 1);
    }

    synchronized boolean isEmpty()
    {
        return queue.isEmpty();
    }

    long getSeqNo()
    {
        return seqNo++;
    }
    
}