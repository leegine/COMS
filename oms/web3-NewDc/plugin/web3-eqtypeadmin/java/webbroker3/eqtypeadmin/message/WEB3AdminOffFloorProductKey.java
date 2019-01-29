head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������L�[(WEB3AdminOffFloorProductKey.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;

/**
 * (�Ǘ��җ���O���������L�[)<BR>
 * <BR>
 * �Ǘ��җ���O���������L�[�B<BR>
 * <BR>
 * WEB3AdminOffFloorProductKey<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductKey extends Message
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductRegistInfoUnit.class);

    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h�B<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h�B<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (��t�I������)<BR>
     * <BR>
     * ��t�I�������B<BR>
     * <BR>
     * orderEndDatetime<BR>
     * <BR>
     */
    public Date orderEndDatetime;

    /**
     * @@roseuid 421AE32F035E
     */
    public WEB3AdminOffFloorProductKey()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�����R�[�h�`�F�b�N <BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�����R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h��null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N <BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�s��R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�s��R�[�h��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O��throw����B <BR>
     *          �E"1�F����" <BR>
     *          �E"2�F���" <BR>
     *          �E"3�F���É�" <BR>
     *          �E"6�F����" <BR>
     *          �E"8�F�D�y" <BR>
     *          �E"9�FNNM" <BR>
     *          �E"10�FJASDAQ" <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �R�j�@@��t�I�������`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.��t�I��������null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u��t�I��������null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01452<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * <BR>
     * �@@1-1)If this.productCode��null,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "productCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 2)marketCode check<BR>
     * <BR>
     * �@@2-1)If this.marketCode��null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "marketCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@2-2)If this.marketCode ��null and it has a value other than the
     * followings,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "marketCode has an undefined value"<BR>
     *          �E1: Def.TOKYO<BR>
     *          �E2: Def.OSAKA<BR>
     *          �E3: Def.NAGOYA<BR>
     *          �E6: Def.FUKUOKA<BR>
     *          �E8: Def.SAPPORO<BR>
     *          �E9: Def.NNM<BR>
     *          �E10: Def.JASDAQ<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * 3)orderEndDatetime check<BR>
     * <BR>
     * �@@3-1)If this.orderEndDatetime��null,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "orderEndDatetime is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01452<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41B7C3740059
     */
    public void validate() throws WEB3BusinessLayerException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1)If this.productCode��null, throw Exception
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //2-1)If this.marketCode��null,
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            /*
             *  2-2)If marketCode has a value other than the
             *  followings Throw the exception
             * .1: Def.TOKYO
             * �E2: Def.OSAKA
             * �E3: Def.NAGOYA
             * �E6: Def.FUKUOKA
             * �E8: Def.SAPPORO
             * �E9: Def.NNM
             * �E10: Def.JASDAQ
             */
        } else
        {
            if ((!WEB3MarketCodeDef.TOKYO.equals(marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //3-1) If this.orderEndDatetime��null
        if (orderEndDatetime == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01452,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
