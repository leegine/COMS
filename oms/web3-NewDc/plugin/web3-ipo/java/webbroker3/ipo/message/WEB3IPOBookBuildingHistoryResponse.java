head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�u�b�N�r���f�B���O�\���������X�|���X�N���X(WEB3IPOBookBuildingHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO�u�b�N�r���f�B���O�\���������X�|���X�N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingHistoryResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_bookBuildingHistory";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200408101038L;
    /**
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * �u�b�N�r���f�B���O�\�������ꗗ
     */
    public WEB3IPODemandHistoryUnit[] bookBuildingHistoryList;
    
    /**
     * @@roseuid 4112E4E1011A
     */
    public WEB3IPOBookBuildingHistoryResponse() 
    {
     
    }
    
    /**
     * (IPO�u�b�N�r���f�B���O�\���������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40DC052A00AC
     */
    public WEB3IPOBookBuildingHistoryResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
