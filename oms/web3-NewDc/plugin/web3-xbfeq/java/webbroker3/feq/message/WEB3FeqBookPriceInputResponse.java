head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^���̓��X�|���X(WEB3FeqBookPriceInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������뉿�P���o�^���̓��X�|���X)<BR>
 * �O�������뉿�P���o�^���̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBookPriceInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_bookPriceInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�ۗL���YID)<BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (�c������)<BR>
     * �c������<BR>
     */
    public String balanceQuantity;
    
    /**
     * (���t�\����)<BR>
     * ���t�\����<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (����������)<BR>
     * ����������<BR>
     */
    public String orderedQuantity;
    
    /**
     * (���t�s�\����)<BR>
     * ���t�s�\����<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (���͕뉿�P��)<BR>
     * ���͕뉿�P��<BR>
     * <BR>
     * ���O����͂����l��\���B<BR>
     * �@@�����͂̏ꍇ��null���Z�b�g�B<BR>
     */
    public String inputBookPrice = null;
    
    /**
     * (�뉿�P�����͓���)<BR>
     * �뉿�P�����͓���<BR>
     * <BR>
     * ���O����͂���������\���B<BR>
     * �@@�����͂̏ꍇ��null���Z�b�g�B<BR>
     */
    public Date bookPriceInputDate = null;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * @@roseuid 42CE3A05000F
     */
    public WEB3FeqBookPriceInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqBookPriceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
