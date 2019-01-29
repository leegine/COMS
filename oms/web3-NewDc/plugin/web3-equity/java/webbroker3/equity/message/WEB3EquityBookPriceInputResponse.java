head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������뉿�P���o�^���̓��X�|���X(WEB3EquityBookPriceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
                 : 2006/08/29 �����F(���u) ���f�� 972
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i���������뉿�P���o�^���̓��X�|���X�j�B<BR>
 * <BR>
 * ���������뉿�P���o�^���̓��X�|���X�N���X<BR>
 */
public class WEB3EquityBookPriceInputResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_book_price_input";
    
    /**
     * (�ۗL���YID)<BR>
     * <BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * <BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;
    
    /**
     * (�c������)<BR>
     * <BR>
     * �c������<BR>
     */
    public String balanceQuantity;
    
    /**
     * (���t�\����)<BR>
     * <BR>
     * ���t�\����<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (����������)<BR>
     * <BR>
     * ����������<BR>
     */
    public String orderedQuantity;
    
    /**
     * (���t�s�\����)<BR>
     * <BR>
     * ���t�s�\����<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (���͕뉿�P��)<BR>
     * <BR>
     * ���͕뉿�P��<BR>
     * <BR>
     * ���O����͂����l��\���B<BR>
     * �@@�����͂̏ꍇ��null���Z�b�g�B<BR>
     */
    public String inputBookPrice = null;
    
    /**
     * (�뉿�P�����͓���)<BR>
     * <BR>
     * �뉿�P�����͓���<BR>
     * <BR>
     * ���O����͂���������\���B<BR>
     * �@@�����͂̏ꍇ��null���Z�b�g�B<BR>
     */
    public Date bookPriceInputDate = null;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * <BR>
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * @@roseuid 4206CF2F004A<BR>
     */
    public WEB3EquityBookPriceInputResponse() 
    {
     
    }
    
    public WEB3EquityBookPriceInputResponse(WEB3EquityBookPriceInputRequest l_request)
    {
        super(l_request);
    }
}
@
