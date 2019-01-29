head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����������Ɖ���̓��X�|���X (WEB3AdminOREquityOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/09 �����q(���u) �d�l�ύX ���f��No.106
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����������Ɖ���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�����������Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�������ꗗ)<BR>
     * <BR>
     * �������̔z��<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * <BR>
     * �s��R�[�h�̔z��<BR>
     * <BR>
     * An array of marketCode<BR>
     * <BR>
     */
    public String[] marketCodeList;

    /**
     * (�ٍψꗗ)<BR>
     * <BR>
     * �ٍϋ敪�̔z��<BR>
     * <BR>
     * An array of repayment<BR>
     * <BR>
     */
    public String[] repaymentList = null;

    /**
     * (�l�i�����ꗗ)<BR>
     * <BR>
     * �l�i�����̔z��<BR>
     * <BR>
     * An array of priceCond<BR>
     * <BR>
     */
    public String[] priceCondList = null;

    /**
     * (���s�����ꗗ)<BR>
     * <BR>
     * ���s�����̔z��<BR>
     * <BR>
     * An array of execCondList<BR>
     * <BR>
     */
    public String[] execCondList = null;

    /**
     * (���������敪�ꗗ)<BR>
     * <BR>
     * ���������敪�̔z��<BR>
     * <BR>
     * An array of expirationDateType<BR>
     * <BR>
     */
    public String[] expirationDateTypeList = null;

    /**
     * (���������ꗗ)<BR>
     * <BR>
     * ���������̔z��<BR>
     * <BR>
     * An array of orderCondType<BR>
     * <BR>
     */
    public String[] orderCondTypeList = null;

    /**
     * (�����o�H�敪�ꗗ)<BR>
     * <BR>
     * �����o�H�敪�̈ꗗ<BR>
     */
    public String[] orderRootList = null;
    
    /**
     * �i�戵���i�ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * (�������ώ��{�t���O)<BR>
     * <BR>
     * false:�����{<BR>
     * true:���{<BR>
     */
    public boolean forcedSettleEnforcementFlag;

    /**
     * @@roseuid 4212FB420315
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
