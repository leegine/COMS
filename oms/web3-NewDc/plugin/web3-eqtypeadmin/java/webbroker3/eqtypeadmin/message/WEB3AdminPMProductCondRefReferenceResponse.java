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
filename	WEB3AdminPMProductCondRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������Ɖ�X�|���X (WEB3AdminPMProductCondRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������Ɖ�X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�������������Ɖ�X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondRefReferenceResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefReferenceResponse extends WEB3GenResponse
{
    /**
      * PTYPE<BR>
      */
    public final static String PTYPE = "admin_pm_product_cond_ref_reference";

    /**
      * serialVersionUID<BR>
      */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

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
     * �i�c�Ɠ��ꗗ�j<BR>
     * <BR>
     * �c�Ɠ��̈ꗗ<BR>
     * <BR>
     * �����c�Ɠ��A���c�Ɠ��A���X�c�Ɠ��̏��Ŋi�[<BR>
     * <BR>
     * bixDateList<BR>
     * <BR>
     * Stock in the following order<BR>
     * bizDate, nextBizDate, Next2BizDate<BR>
     * <BR>
     */
    public Date[] bizDateList;

    /**
     * �i���Z���j<BR>
     * <BR>
     * ���Z��<BR>
     * <BR>
     * settlementDate<BR>
     * <BR>
     */
    public Date settlementDate;

	/**
	* �i�D��s��j<BR>
	* �D��s�� <BR>
	*<BR>
	*  1�F�@@����<BR> 
	*  2�F�@@���<BR>
	*  3�F�@@���É�<BR>
	*  6�F�@@����<BR>
	*  8�F�@@�D�y<BR>
	*  9�F�@@NNM<BR>
	* 10�F�@@JASDAQ<BR>
	*/
   public String primaryMarket;

    /**
     * �i��������������~�j<BR>
     * <BR>
     * 0�F�@@��~�łȂ�<BR>
     * 1�F�@@��~��(������K��)<BR>
     * 2�F�@@��~��(���ЋK��)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * equityDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String equityDealingStatus;

    /**
     * �i���x�M�p������~�j<BR>
     * <BR>
     * ���x�M�p������~<BR>
     * <BR>
     * 0�F�@@��~�łȂ�<BR>
     * 1�F�@@��~��(������K��)<BR>
     * 2�F�@@��~��(���ЋK��)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketMarginDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String marketMarginDealingStatus = null;

    /**
     * �i��ʐM�p������~�j<BR>
     * <BR>
     * ��ʐM�p������~<BR>
     * <BR>
     * 0�F�@@��~�łȂ�<BR>
     * 1�F�@@��~��(������K��)<BR>
     * 2�F�@@��~��(���ЋK��)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * institutionMarginDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String institutionMarginDealingStatus = null;

    /**
     * �i��������戵�K���j<BR>
     * <BR>
     * ��������戵�K��<BR>
     * <BR>
     * 0�F�@@�戵�s��<BR>
     * 1�F�@@�戵�\<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * capitalGainRegulation<BR>
     * <BR>
     * 0: Def.DISABLE<BR>
     * 1: Def.NORMAL<BR>
     * <BR>
     */
    public String capitalGainRegulation;

    /**
     * �i��p�|�ځj<BR>
     * <BR>
     * ��p�|��<BR>
     * <BR>
     * substituteAssessmentRate<BR>
     * <BR>
     */
    public String substituteAssessmentRate = null;

    /**
     * �i�a��،��]���|�ځj<BR>
     * <BR>
     * �a��،��]���|��<BR>
     * <BR>
     * depositSecuritiesAssessmentRate<BR>
     * <BR>
     */
    public String depositSecuritiesAssessmentRate = null;

    /**
     * �i��p�]���P���j<BR>
     * <BR>
     * ��p�]���P��<BR>
     * <BR>
     * substituteSecurityAssetPrice<BR>
     * <BR>
     */
    public String substituteSecurityAssetPrice = null;

	/**
	 * �i�~�j�~�j�����{�t���O�j<BR>
	 * <BR>
	 * �~�j�����{�t���O<BR>
	 * <BR>
	 * false�F�����{<BR>
	 * true �F���{<BR>
	 * <BR>
	 * ----<English>--------------------<BR>
	 * <BR>
	 * miniFlag<BR>
	 * <BR>
	 * false�FFALSE<BR>
	 * true �FTRUE<BR>
	 * <BR>
	 */

	public boolean miniFlag;

    /**
     * �i�~�j���������ꗗ�j<BR>
     * <BR>
     * �~�j���������̈ꗗ<BR>
     * ���~�j�������{��Ђ̏ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * miniProductInfo list<BR>
     * ��Set null for the mini share unexecution company. <BR>
     * <BR>
     */
    public WEB3AdminPMProductRegistInfoUnit[] miniProductInfoList = null;

    /**
     * �i�s��ʖ��������Ɖ���ꗗ�j
     * �s��ʖ��������Ɖ���ꗗ
     * ----<English>--------------------
     * marketProductCondInfoList
     */
    public WEB3AdminPMProductCondInfoUnit[] marketProductCondInfoList;

    /**
     * �i��r���ʏ��ꗗ�j
     * ��r���ʏ��ꗗ
     * ----<English>--------------------
     * compResultInfoList
     */
    public WEB3AdminPMCompResultInfoUnit[] compResultInfoList;

    /**
     * @@roseuid 41FA2E1502BF
     */
    public WEB3AdminPMProductCondRefReferenceResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
