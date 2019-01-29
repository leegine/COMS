head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������ݒ���̓��X�|���X(WEB3AdminPMProductCondConfInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������ݒ���̓��X�|���X) <BR>
 * <BR>
 * �Ǘ��ҁE�������������ݒ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * �i�c�Ɠ��j<BR>
     * <BR>
     * �{���̉c�Ɠ�<BR>
     * <BR>
     * bizDate<BR>
     * <BR>
     */
    public Date bizDate;

    /**
     * �i���c�Ɠ��j<BR>
     * <BR>
     * �����̉c�Ɠ�<BR>
     * <BR>
     * nextBizDate<BR>
     * <BR>
     */
    public Date nextBizDate;

    /**
     * �i���X�c�Ɠ��j<BR>
     * <BR>
     * ���X���̉c�Ɠ�<BR>
     * <BR>
     * next2BizDate<BR>
     * <BR>
     */
    public Date next2BizDate;

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�������j<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * ��������ꂵ�Ă���s��̎s��R�[�h�̔z��<BR>
     * <BR>
     * The array of marketCode of markets in which products list<BR>
     * <BR>
     */
    public String[] listingCodeList;

    /**
     * �i���x�M�p���{�t���O�j<BR>
     * <BR>
     * ���x�M�p���{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marketMarginFlag;

    /**
     * �i��ʐM�p���{�t���O�j<BR>
     * <BR>
     * ��ʐM�p���{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * institutionMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean institutionMarginFlag;

    /**
     * �i�~�j�����{�t���O�j<BR>
     * <BR>
     * �~�j�����{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag;

    /**
     * �i����K���ꗗ�j<BR>
     * <BR>
     * ������������K���̈ꗗ<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * �i��{���ꗗ�j<BR>
     * <BR>
     * ����������{���̈ꗗ<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * �i�M�p�������ꗗ�j<BR>
     * <BR>
     * ���������M�p�������̈ꗗ<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * �i�ϑ��ۏ؋����ꗗ�j<BR>
     * <BR>
     * ���������ϑ��ۏ؋����̈ꗗ<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * �i��p�L���،����ꗗ�j<BR>
     * <BR>
     * ����������p�L���،����̈ꗗ<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * �i�l�i���ꗗ�j<BR>
     * <BR>
     * ���������l�i���̈ꗗ<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA2A3501A6
     */
    public WEB3AdminPMProductCondConfInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondConfInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
