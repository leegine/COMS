head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondQueryContainer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������R���e�i(WEB3AdminBondQueryContainer.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q (���u) �V�K�쐬
*/

package webbroker3.bd;


/**
 * (���������R���e�i)<BR>
 * ���������R���e�i�N���X<BR>
 * <BR>
 * ��������������ƌ��������f�[�^��ێ�����R���e�i
 * 
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminBondQueryContainer 
{
    
    /**
     * (��������������)<BR>
     * ��������������B(SQL��where��)
     */
    private String queryString;
    
    /**
     * (�����f�[�^�z��)<BR>
     * �����f�[�^�z��
     */
    private Object[] queryData;
    
    /**
     * (���������R���e�i)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 44CFF7C80126
     */
    public WEB3AdminBondQueryContainer() 
    {
     
    }
    
    /**
     * (get��������������)<BR>
     * get��������������<BR>
     * <BR>
     * �@@���������������Ԃ�
     * @@return String
     * @@roseuid 44D41BE8003D
     */
    public String getQueryString() 
    {
        return this.queryString;
    }
    
    /**
     * (set��������������)<BR>
     * set��������������<BR>
     * <BR>
     * �@@����������������Z�b�g����
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������
     * @@roseuid 44D41C0F000F
     */
    public void  setQueryString(String l_strQueryString) 
    {
        this.queryString = l_strQueryString;
    }
    
    /**
     * (get���������f�[�^)<BR>
     * get���������f�[�^<BR>
     * <BR>
     * �@@���������f�[�^��Ԃ�
     * @@return String
     * @@roseuid 44D41C3900F9
     */
    public Object[] getQueryData() 
    {
        return this.queryData;
    }
    
    /**
     * (set���������f�[�^)<BR>
     * set���������f�[�^<BR>
     * <BR>
     * �@@���������f�[�^���Z�b�g����
     * @@param l_objQueryData - (���������f�[�^)<BR>
     * ���������f�[�^
     * @@roseuid 44D41C390128
     */
    public void setQueryData(Object[] l_objQueryData) 
    {
        this.queryData = l_objQueryData;
    }
}
@
