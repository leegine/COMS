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
filename	WEB3EquityBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������c���Ɖ�X�|���X(WEB3EquityBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/


package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i���������c���Ɖ�X�|���X�j�B<BR>
 * <BR>
 * ���������c���Ɖ�X�|���X�N���X<BR>
 */
public class WEB3EquityBalanceReferenceResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_balance_reference";
    
    /**
     * (�����ꗗ)<BR>
     *<BR>
     * �������������R�[�h���̂̔z��<BR>
     */
    public WEB3EquityProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     *<BR>
     * �s��R�[�h�̔z��<BR>
     */
    public String[] marketList;
    
    /**
     * (�c���Ɖ��)<BR>
     *<BR>
     * ���������c���Ɖ�ׂ̔z��<BR>
     */
    public WEB3EquityBalanceReferenceDetailUnit[] equityBalanceReferenceDetail;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     *<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex = "0";

  /**
    * (���y�[�W��)
    */
    public String totalPages = "0";

  /**
    * (�����R�[�h��)
    */
    public String totalRecords= "0";
    
    /**
     * @@roseuid 4206C8AA01B1<BR>
     */
    public WEB3EquityBalanceReferenceResponse() 
    {
     
    }
    
    public WEB3EquityBalanceReferenceResponse(WEB3EquityBalanceReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
