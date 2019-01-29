head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ����]�͉�ʕ\�����X�|���X(WEB3TPTradingPowerResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (����]�͉�ʕ\�����X�|���X) <BR>
 * ����]�͉�ʕ\�����X�|���X�N���X�B <BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTradingPowerResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_tradingpower";

    /**
     * �]�͌v�Z����ID
     */
    public String calcResultId;

    /**
     * ����]�͖��׈ꗗ
     */
    public WEB3TPTradingPowerUnit[] tradingPowerUnits;

    /**
     * ����������n��
     */
    public Date equityDeliveryDate;

    /**
     * �M�p�V�K�����
     */
    public Date marginBaseDate;

    /**
     * �~�j����n��
     */
    public Date mstkDeliveryDate;

    /**
     * �I�v�V������n��
     */
    public Date optionsDeliveryDate;

    /**
     * ���M��n��
     */
    public Date mutualDeliveryDate;

    /**
     * �ݓ���n��
     */
    public Date ruitoDeliveryDate;

    /**
     * IPO���
     */
    public Date ipoBaseDate;

    /**
     * ����������n��
     */
    public Date feqDeliveryDate;

    /**
     * �o����n��
     */
    public Date aioDeliveryDate;

    /**
     * (�R���X�g���N�^)
     * 
     * @@param l_request
     * @@roseuid 41B6993D034B
     */
    protected WEB3TPTradingPowerResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (�R���X�g���N�^)
     * 
     * @@roseuid 41B681F5036B
     */
    public WEB3TPTradingPowerResponse()
    {

    }

}@
