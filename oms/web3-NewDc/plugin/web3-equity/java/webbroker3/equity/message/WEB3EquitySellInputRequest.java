head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������͗v���@@���N�G�X�g�f�[�^�N���X(WEB3EquitySellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
Revesion History : 2004/12/08 �����a��(SRA) �c�Č��Ή� �m��.�O�T�V
Revesion History : 2007/12/17 ���n(���u) ���f��No.1210
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������t�������̓��N�G�X�g�j�B<BR>
 * <BR>
 * �����������t�������͗v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellInputRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellInputRequest.class);

    /**
     * (ID)<BR>
     * �ۗL���YID<BR>
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
    public static final String PTYPE = "equity_order_sellinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200404301200L;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 406118E702BB
     */
    public WEB3EquitySellInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.equity.message.WEB3EquitySellInputResponse
     * @@roseuid 40602C24036C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellInputResponse(this);
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     * 
     * �P�j�@@ID�`�F�b�N 
     * �@@this.ID��null�̏ꍇ�A <BR> 
     * �@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00080<BR>
     * 
     * �Q�j�@@�s��R�[�h�`�F�b�N 
     * �@@this.�s��R�[�h��null�A 
     * �@@�����L�̒l�ȊO�̏ꍇ�A 
     * �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B 
     * �@@�@@�@@�E�P�F���� 
     * �@@�@@�@@�E�Q�F��� 
     * �@@�@@�@@�E�R�F���É� 
     * �@@�@@�@@�E�U�F���� 
     * �@@�@@�@@�E�W�F�D�y 
     * �@@�@@�@@�E�X�FNNM 
     * �@@�@@�@@�E�P�O�FJASDAQ
     * �@@�@@�@@�E�P�P�FJNX-PTS
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.marketCode != null)
        {
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
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
