head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecSortKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[���ڒ�`�C���^�t�F�C�X(WEB3AdminDirSecSortKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History :  2006/03/31 ���r(���u) �V�K�쐬   
Revesion History :  2008/04/18 �Ԑi(���u) �d�l�ύX ���f��No.116 
Revesion History :  2008/07/22 ����(���u) �d�l�ύX ���f��No.132
*/
package webbroker3.dirsec.define;

/**
 * (�L�[����)<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0
 */
public interface WEB3AdminDirSecSortKeyItemDef
{
    
    /**
     * �e�[�u����
     */
    public static final String HOST_TABLE_NAME = "tableJpnName"; 
    
    /**
     * �e�[�u��������
     */
    public static final String HOST_TABLE_PHYSICS_NAME = "tableName"; 
    
    /**
     * ���ʃR�[�h
     */
    public static final String ORDER_REQUEST_NUMBER = "identityCode";
    
    /**
     * �X�e�[�^�X
     */
    public static final String BEFORESTATUS = "beforeStatus";
    
    /**
     * �쐬���t
     */
    public static final String CREATED_TIMESTAMP = "createDate";

    /**
     * �f�[�^�R�[�h
     */
    public static final String DATA_CODE = "dataCode";

    /**
     * �Ĕ��s�\�t���O
     */
    public static final String REISSUE_POSSIBLE_FLAG = "reissuePossibleFlag";

    /**
     * �`�[�R�[�h
     */
    public static final String REQUEST_CODE = "requestCode";

    /**
     * PTYPE
     */
    public static final String PTYTE = "pType";

    /**
     * AP���菈����
     */
    public static final String AP_NAME = "apName";

}
@
