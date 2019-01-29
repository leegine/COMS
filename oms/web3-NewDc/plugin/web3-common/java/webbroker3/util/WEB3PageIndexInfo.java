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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �y�[�W���߂��鏈���N���X(WEB3PageIndexInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ����� (���u)  �V�K�쐬
                   
*/
package webbroker3.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.ListPage;

/**
 * �y�[�W���߂��鏈���N���X�B<BR>
 * 
 * @@author ����� (���u)
 * @@version 1.0
 */
public class WEB3PageIndexInfo
{

    /**
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    private int pageIndex;

    /**
     * �y�[�W���\���s��<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    private int pageSize;

    /**
     * ���y�[�W��
     */
    private int totalPages;

    /**
     * �����R�[�h��
     */
    private int totalRecords;

    /**
     * ���׃f�[�^�ꗗ�̃��X�g<BR>
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
     *  �v���ɂ��A�z��̃y�[�W���߂��鏈�����s����<BR>
     * 
     * @@param l_objs ���ׂ̔z��
     * @@param l_intRequestPageIndex �v���y�[�W�ԍ�
     * @@param l_intRequestPageSize �v���y�[�W���\���s��
     */
    public WEB3PageIndexInfo(
        Object[] l_objs,
        int l_intRequestPageIndex,
        int l_intRequestPageSize)
    {
        this.process(l_objs, l_intRequestPageIndex, l_intRequestPageSize);
    }

    /**
     * �v���ɂ��A���X�g�̃y�[�W���߂��鏈�����s����<BR>
     * 
     * @@param l_list ���׃��X�g
     * @@param l_intRequestPageIndex �v���y�[�W�ԍ�
     * @@param l_intRequestPageSize �v���y�[�W���\���s��
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

        // ���y�[�W��: 
        // -start--------------------------------------------- 
        // ���ׂ̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g
        if (l_listPage.totalSize() == 0)
        {
            this.totalPages = 0;
        }
        // ���ׂ̗v�f�����y�[�W���\���s��
        else if (l_listPage.totalSize() % l_intRequestPageSize == 0)
        {
            this.totalPages = l_listPage.totalSize() / l_intRequestPageSize;
        }
        //���]�肪�o��ꍇ�́A�{�P�����l��ݒ�
        else
        {
            this.totalPages = l_listPage.totalSize() / l_intRequestPageSize + 1;
        }
        //-end----------------------------------------------- 
        // �v���y�[�W�ԍ�: 
        this.pageSize = l_intRequestPageSize;
        //�����R�[�h��:�@@���ׂ̗v�f�� 
        this.totalRecords = l_listPage.totalSize();

        //�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
        //-start--------------------------------------------- 
        // �����̖��ׂ̗v�f�� > �����̃y�[�W���\���s��
        // (�����̗v���y�[�W�ԍ� - 1) )�ł���΁A
        //  �����̗v���y�[�W�ԍ��B 
        // ��L�ȊO�̏ꍇ�́A���y�[�W�� �����̂܂ܐݒ�B 
        // ���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A�ŏI�y�[�W�ɊY������f�[�^�� 
        // ����N���X�ɐݒ肷��B 
        // -end----------------------------------------------- 

        //�����̗v���y�[�W�ԍ�
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

        // ���y�[�W��: 
        // -start--------------------------------------------- 
        // ���ׂ̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g
        if (l_objs.length == 0)
        {
            this.totalPages = 0;
        }
        // ���ׂ̗v�f�����y�[�W���\���s��
        else if (l_objs.length % l_intRequestPageSize == 0)
        {
            this.totalPages = l_objs.length / l_intRequestPageSize;
        }
        //���]�肪�o��ꍇ�́A�{�P�����l��ݒ�
        else
        {
            this.totalPages = l_objs.length / l_intRequestPageSize + 1;
        }
        //-end----------------------------------------------- 
        // �v���y�[�W�ԍ�: 
        this.pageSize = l_intRequestPageSize;
        //�����R�[�h��:�@@���ׂ̗v�f�� 
        this.totalRecords = l_objs.length;

        //�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
        //-start--------------------------------------------- 
        // �����̖��ׂ̗v�f�� > �����̃y�[�W���\���s��
        // (�����̗v���y�[�W�ԍ� - 1) )�ł���΁A
        //  �����̗v���y�[�W�ԍ��B 
        // ��L�ȊO�̏ꍇ�́A���y�[�W�� �����̂܂ܐݒ�B 
        // ���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A�ŏI�y�[�W�ɊY������f�[�^�� 
        // ����N���X�ɐݒ肷��B 
        // -end----------------------------------------------- 

        //�����̗v���y�[�W�ԍ�
        this.pageIndex =
            (l_intRequestPageIndex > this.totalPages)
                ? this.totalPages
                : l_intRequestPageIndex;
        this.pageIndex = (this.pageIndex < 1) ? 1 : this.pageIndex;

        /* �����̃y�[�W���\���s���~( 
         * �\���y�[�W�ԍ� - 1)�����A
         * ���׃f�[�^�ꗗ�̗v�f���X�L�b�v����B
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
