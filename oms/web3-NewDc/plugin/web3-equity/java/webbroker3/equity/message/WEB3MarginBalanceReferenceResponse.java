head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c���Ɖ�X�|���X(WEB3MarginBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�M�p����c���Ɖ�X�|���X�j�B<BR>
 * <BR>
 * �M�p����c���Ɖ�X�|���X�N���X<BR>
 */
public class WEB3MarginBalanceReferenceResponse extends WEB3GenResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference";

    /**
     * (�����ꗗ)<BR>
     * <BR>
     * �������������R�[�h���̂̔z��<BR>
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * <BR>
     * �s��R�[�h�̔z��<BR>
     */
    public String[] marketList;
    
    /**
     * (�c���Ɖ��)<BR>
     * <BR>
     * ���������c���Ɖ�ׂ̔z��<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit[] balanceReference;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex = "0";

    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages = "0";

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords = "0";
    
    /**
     * @@roseuid 4206CDBB02BB<BR>
     */
    public WEB3MarginBalanceReferenceResponse() 
    {
     
    }
    
    public WEB3MarginBalanceReferenceResponse(WEB3MarginBalanceReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
