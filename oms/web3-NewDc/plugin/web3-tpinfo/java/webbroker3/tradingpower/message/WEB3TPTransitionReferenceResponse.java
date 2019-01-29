head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐��ډ�ʕ\�����X�|���X(WEB3TPTransitionReferenceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�]�͐��ډ�ʕ\�����X�|���X)<BR>
 * �]�͐��ډ�ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPTransitionReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_transition_reference";

    /**
     * �]�͌v�Z����ID
     */
    public String calcResultId;

    /**
     * �a��،��ڋq�敪
     */
    public String depositDiv;

    /**
     * �]�͐��ږ��׈ꗗ
     */
    public WEB3TPTransitionReferenceUnit[] transitionReferenceUnits;

    /**
     * �]�͓K�p��<���������t�]��>
     */
    public Date equityBuyApplyDate;

    /**
     * �]�͓K�p��<�M�p�V�K���]��>
     */
    public Date marginApplyDate;

    /**
     * �]�͓K�p��<�M�p�����]��>
     */
    public Date swapLongApplyDate;

    /**
     * �]�͓K�p��<���M���t�]��>
     */
    public Date mutualBuyApplyDate;

    /**
     * �]�͓K�p��<���̑����i���t�]��>
     */
    public Date otherApplyDate;

    /**
     * �]�͓K�p��<�o���]��>
     */
    public Date paymentApplyDate;

    /**
     * �]�͓K�p��<�ۏ؋��a����>
     */
    public Date marginCollateralRateApplyDate;

    /**
     * �l�􂢏�ԋ敪
     */
    public String markToMarketStateDiv;

    /**
     * (�،��S�ۃ��[���敪) 
     * 
     * 0 : �����{ 
     * 1 : ���{ 
     * (�ڋq���ނ̔���Ɏg�p)
     */
    public String securedLoanSecAccOpenDiv;

    /**
     * (�R���X�g���N�^)
     * @@param l_request
     * @@roseuid 41B6A87D008C
     */
    protected WEB3TPTransitionReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (�R���X�g���N�^)
     * @@roseuid 41B55B630046
     */
    public WEB3TPTransitionReferenceResponse()
    {

    }
}@
