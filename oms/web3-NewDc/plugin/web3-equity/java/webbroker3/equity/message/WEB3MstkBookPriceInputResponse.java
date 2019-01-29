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
filename	WEB3MstkBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����뉿�P���o�^���̓��X�|���X(WEB3MstkBookPriceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�����~�j�����뉿�P���o�^���̓��X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����뉿�P���o�^���̓��X�|���X�N���X<BR>
 */
public class WEB3MstkBookPriceInputResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_book_price_input";
    
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
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (�c������)<BR>
     * <BR>
     * �c������<BR>
     */
    public String balanceQuantity;
    
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
     * @@roseuid 4206CF45029C<BR>
     */
    public WEB3MstkBookPriceInputResponse() 
    {
     
    }
    
    public WEB3MstkBookPriceInputResponse(WEB3MstkBookPriceInputRequest l_request)
    {
        super(l_request);
    }
}
@
