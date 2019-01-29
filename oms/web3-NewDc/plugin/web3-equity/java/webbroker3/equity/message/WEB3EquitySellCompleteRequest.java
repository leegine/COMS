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
filename	WEB3EquitySellCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����������N�G�X�g(WEB3EquitySellCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2007/12/17 ���n(���u) ���f��No.1205
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������t�����������N�G�X�g�j�B<br>
 * <br>
 * �����������t���������v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellCompleteRequest extends WEB3EquityCommonRequest
{

    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellCompleteRequest.class);

    /**
     * �i�����h�c�j�B
     * <br>
     * �m�F���X�|���X�Ŏ�M�����l�����̂܂ܑ��M<br>
     */
    public String orderId;

    /**
     * �i�h�c�j�B
     * <br>
     * ���YID<br>
     */
    public String id;

    /**
     * �i�s��R�[�h�j�B
     * <br>
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ
     */
    public String marketCode;

    /**
     * �i�m�F���P���j�B<br>
     * <br>
     * �m�F���̒P��
     */
    public String checkPrice;

    /**
     * �i�m�F���������j�B<br>
     * <br>
     * �m�F��������
     */
    public Date checkDate;

    /**
     * �i�Ïؔԍ��j�B<br>
     * <br>
     * �Ïؔԍ��i����p�X���[�h�j���͒l
     */
    public String password;

    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "equity_sell_complete";

    /**
     * �iserialVersionUID�j�B
     */
    public static final long serialVersionUID = 200402041600L;

    /**
     * �i�����������t�����������N�G�X�g�j�B<br>
     * �R���X�g���N�^
     * @@roseuid 406118D501A2
     */
    public WEB3EquitySellCompleteRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return WEB3EquityOrder
     * @@roseuid 40602CAC0196
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellCompleteResponse(this);
    }
    
    /**
     * �ivalidate�j�B<br>
     * <br>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<br>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B<br> 
     * <br>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<br>
     * <br>
     * �Q�j�@@ID�`�F�b�N<br>
     * �@@this.ID��null�̏ꍇ�A<br>
     * �@@�uID��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@class : WEB3BusinessLayerException<br>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00080<br>
     * <br>
     * �R�j�@@�s��R�[�h�`�F�b�N<br>
     * �@@�R�|�P�jthis.�s��R�[�h��null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@class : WEB3BusinessLayerException<br>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00443<br>
     * <br>
     * �@@�R�|�Q�jthis.�s��R�[�h��null�A<br>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�E�P�F����<br>
     * �@@�@@�@@�@@�E�Q�F���<br>
     * �@@�@@�@@�@@�E�R�F���É�<br>
     * �@@�@@�@@�@@�E�U�F����<br>
     * �@@�@@�@@�@@�E�W�F�D�y<br>
     * �@@�@@�@@�@@�E�X�FNNM<br>
     * �@@�@@�@@�@@�E�P�O�FJASDAQ<br>
     * �@@�@@�@@�@@�E�P�P�FJNX-PTS<br>
     * �@@�@@�@@class : WEB3BusinessLayerException<br>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<br>
     * <br>
     * �S�j�@@�m�F���P���`�F�b�N�ithis.����ID��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B�j<br>
     * �@@this.�m�F���P����null�̏ꍇ�A<br>
     * �@@�u�m�F���P����null�v�̗�O���X���[����B<br>
     * �@@�@@�@@class : WEB3BusinessLayerException<br>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00206<br>
     * <br>
     * �T�j�@@�m�F���������`�F�b�N�ithis.����ID��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B�j<br>
     * �@@this.�m�F����������null�̏ꍇ�A<br>
     * �@@�u�m�F����������null�v�̗�O���X���[����B<br>
     * �@@�@@�@@class : WEB3BusinessLayerException<br>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00078<br>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null)
        {
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
        
        if (this.orderId != null)
        {
            if(this.checkPrice == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.checkDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                    this.getClass().getName() + ".validate()");
            }
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
     * �Q�j�@@����ID�`�F�b�N<BR>
     * �@@this.����ID��null�̏ꍇ�A<BR>
     * �@@�u����ID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00600<BR>
     * <BR>
     * �R�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�s��R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02257<BR>
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
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �S�j�@@�m�F���P���`�F�b�N<BR>
     * �@@this.�m�F���P����null�̏ꍇ�A<BR>
     * �@@�u�m�F���P����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00206<BR>
     * <BR>
     * �T�j�@@�m�F���������`�F�b�N<BR>
     * �@@this.�m�F����������null�̏ꍇ�A<BR>
     * �@@�u�m�F����������null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateAtReverseOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (this.orderId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
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
        
        if (this.checkPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
