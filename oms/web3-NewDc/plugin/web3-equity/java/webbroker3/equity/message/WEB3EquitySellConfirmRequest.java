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
filename	WEB3EquitySellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����m�F���N�G�X�g(WEB3EquitySellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2007/12/17 ���n(���u) ���f��No.1205
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������t�����m�F���N�G�X�g�j�B<BR>
 * <BR>
 * �����������t�����m�F�v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellConfirmRequest extends WEB3EquityCommonRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellConfirmRequest.class);

    /**
     * (ID)<BR>
     * ���YID<BR>
     */
    public String id;

    /**
     * (�s��R�[�h)<BR>
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "equity_sell_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041611L;

    /**
     * (�����������t�����m�F���N�G�X�g) <BR>
     * �R���X�g���N�^ <BR>
     * @@roseuid 406118BE024E
     */
    public WEB3EquitySellConfirmRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return WEB3EquityOrder
     * @@roseuid 40602C5D0204
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellConfirmResponse(this);
    }

    /**
     * (validate)<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A<BR>
     * �@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�s��R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�s��R�[�h��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@�@@�E�Q�F���<BR>
     * �@@�@@�@@�@@�E�R�F���É�<BR>
     * �@@�@@�@@�@@�E�U�F����<BR>
     * �@@�@@�@@�@@�E�W�F�D�y<BR>
     * �@@�@@�@@�@@�E�X�FNNM<BR>
     * �@@�@@�@@�@@�E�P�O�FJASDAQ<BR>
     * �@@�@@�@@�@@�E�P�P�FJNX-PTS<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + ".validate()");
        }
        
        if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            || WEB3MarketCodeDef.NNM.equals(this.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT���Ύ��)<BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�s��R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02257<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�s��R�[�h��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@�@@�E�Q�F���<BR>
     * �@@�@@�@@�@@�E�R�F���É�<BR>
     * �@@�@@�@@�@@�E�U�F����<BR>
     * �@@�@@�@@�@@�E�W�F�D�y<BR>
     * �@@�@@�@@�@@�E�X�FNNM<BR>
     * �@@�@@�@@�@@�E�P�O�FJASDAQ<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateAtReverseOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
           || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
           || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
           || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
           || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
           || WEB3MarketCodeDef.NNM.equals(this.marketCode)
           || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
