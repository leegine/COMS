head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PageIndexInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ページをめくる処理クラス(WEB3PageIndexInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 王蘭芬 (中訊)  新規作成
                   
*/
package webbroker3.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.ListPage;

/**
 * ページをめくる処理クラス。<BR>
 * 
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public class WEB3PageIndexInfo
{

    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    private int pageIndex;

    /**
     * ページ内表示行数<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    private int pageSize;

    /**
     * 総ページ数
     */
    private int totalPages;

    /**
     * 総レコード数
     */
    private int totalRecords;

    /**
     * 明細データ一覧のリスト<BR>
     */
    private List listReturned;

    /**
     * @@return Returns the l_lisReturned.
     */
    public List getListReturned()
    {
        return listReturned;
    }
    
    /**
     * @@return Returns the l_objReturned.
     */
    public Object[] getArrayReturned()
    {
        return this.listReturned.toArray();
    }
    
    /**
     * Return the Real Objects<BR>
     * 
     * @@return Returns the l_realObjects.
     */
    public Object[] getArrayReturned(Class l_classType)
    {
        Object[] l_realObjects =
            (Object[]) Array.newInstance(l_classType, this.listReturned.size());
        this.listReturned.toArray(l_realObjects);
        return l_realObjects;
    }

    /**
     * @@return Returns the pageIndex.
     */
    public int getPageIndex()
    {
        return pageIndex;
    }
    /**
     * @@return Returns the pageSize.
     */
    public int getPageSize()
    {
        return pageSize;
    }
    
    /**
     * @@return Returns the totalPages.
     */
    public int getTotalPages()
    {
        return totalPages;
    }
    
    /**
     * @@return Returns the totalRecords.
     */
    public int getTotalRecords()
    {
        return totalRecords;
    }

    /**
     *  要求により、配列のページをめくる処理を行いる<BR>
     * 
     * @@param l_objs 明細の配列
     * @@param l_intRequestPageIndex 要求ページ番号
     * @@param l_intRequestPageSize 要求ページ内表示行数
     */
    public WEB3PageIndexInfo(
        Object[] l_objs,
        int l_intRequestPageIndex,
        int l_intRequestPageSize)
    {
        this.process(l_objs, l_intRequestPageIndex, l_intRequestPageSize);
    }

    /**
     * 要求により、リストのページをめくる処理を行いる<BR>
     * 
     * @@param l_list 明細リスト
     * @@param l_intRequestPageIndex 要求ページ番号
     * @@param l_intRequestPageSize 要求ページ内表示行数
     */
    public WEB3PageIndexInfo(
        List l_list,
        int l_intRequestPageIndex,
        int l_intRequestPageSize)
    {
        if (l_list == null)
        {
            l_list = new Vector();
        }
        if (l_list instanceof ListPage)
        {
            this.process((ListPage)l_list, l_intRequestPageIndex, l_intRequestPageSize);
        }
        else
        {
            Object[] l_objs = l_list.toArray();
            this.process(l_objs, l_intRequestPageIndex, l_intRequestPageSize);
        }
    }

    private void process(
        ListPage l_listPage,
        int l_intRequestPageIndex,
        int l_intRequestPageSize)
    {
        if (l_intRequestPageIndex < 1)
        {
            l_intRequestPageIndex = 1;
        }
        if (l_intRequestPageSize < 1)
        {
            l_intRequestPageSize = l_listPage.totalSize();
        }

        // 総ページ数: 
        // -start--------------------------------------------- 
        // 明細の要素数＝0(表示対象データなし)の場合、0をセット
        if (l_listPage.totalSize() == 0)
        {
            this.totalPages = 0;
        }
        // 明細の要素数÷ページ内表示行数
        else if (l_listPage.totalSize() % l_intRequestPageSize == 0)
        {
            this.totalPages = l_listPage.totalSize() / l_intRequestPageSize;
        }
        //※余りが出る場合は、＋１した値を設定
        else
        {
            this.totalPages = l_listPage.totalSize() / l_intRequestPageSize + 1;
        }
        //-end----------------------------------------------- 
        // 要求ページ番号: 
        this.pageSize = l_intRequestPageSize;
        //総レコード数:　@明細の要素数 
        this.totalRecords = l_listPage.totalSize();

        //表示ページ番号(表示が何ページ目にあたるか): 
        //-start--------------------------------------------- 
        // 引数の明細の要素数 > 引数のページ内表示行数
        // (引数の要求ページ番号 - 1) )であれば、
        //  引数の要求ページ番号。 
        // 上記以外の場合は、総ページ数 をそのまま設定。 
        // ※検索結果がPR層からの要求ページ番号に満たない場合は、最終ページに該当するデータを 
        // これクラスに設定する。 
        // -end----------------------------------------------- 

        //引数の要求ページ番号
        this.pageIndex =
            (l_intRequestPageIndex > this.totalPages)
                ? this.totalPages
                : l_intRequestPageIndex;
        this.pageIndex = (this.pageIndex < 1) ? 1 : this.pageIndex;

        this.listReturned = l_listPage;
    }

    private void process(
        Object[] l_objs,
        int l_intRequestPageIndex,
        int l_intRequestPageSize)
    {
        if (l_objs == null)
        {
            l_objs = new Object[0];
        }
        if (l_intRequestPageIndex < 1)
        {
            l_intRequestPageIndex = 1;
        }
        if (l_intRequestPageSize < 1)
        {
            l_intRequestPageSize = l_objs.length;
        }

        // 総ページ数: 
        // -start--------------------------------------------- 
        // 明細の要素数＝0(表示対象データなし)の場合、0をセット
        if (l_objs.length == 0)
        {
            this.totalPages = 0;
        }
        // 明細の要素数÷ページ内表示行数
        else if (l_objs.length % l_intRequestPageSize == 0)
        {
            this.totalPages = l_objs.length / l_intRequestPageSize;
        }
        //※余りが出る場合は、＋１した値を設定
        else
        {
            this.totalPages = l_objs.length / l_intRequestPageSize + 1;
        }
        //-end----------------------------------------------- 
        // 要求ページ番号: 
        this.pageSize = l_intRequestPageSize;
        //総レコード数:　@明細の要素数 
        this.totalRecords = l_objs.length;

        //表示ページ番号(表示が何ページ目にあたるか): 
        //-start--------------------------------------------- 
        // 引数の明細の要素数 > 引数のページ内表示行数
        // (引数の要求ページ番号 - 1) )であれば、
        //  引数の要求ページ番号。 
        // 上記以外の場合は、総ページ数 をそのまま設定。 
        // ※検索結果がPR層からの要求ページ番号に満たない場合は、最終ページに該当するデータを 
        // これクラスに設定する。 
        // -end----------------------------------------------- 

        //引数の要求ページ番号
        this.pageIndex =
            (l_intRequestPageIndex > this.totalPages)
                ? this.totalPages
                : l_intRequestPageIndex;
        this.pageIndex = (this.pageIndex < 1) ? 1 : this.pageIndex;

        /* 引数のページ内表示行数×( 
         * 表示ページ番号 - 1)数分、
         * 明細データ一覧の要素をスキップする。
         */
        int l_intBeginRecordNumber =
            l_intRequestPageSize * (this.pageIndex - 1);
        int l_intEndRecordNumber = l_intRequestPageSize * this.pageIndex;
        if (l_intEndRecordNumber > this.totalRecords)
        {
            l_intEndRecordNumber = this.totalRecords;
        }

        List l_lisReturns = new Vector();
        for (int i = l_intBeginRecordNumber; i < l_intEndRecordNumber; i++)
        {
            l_lisReturns.add(l_objs[i]);
        }
        this.listReturned = l_lisReturns;
    }
}
@
