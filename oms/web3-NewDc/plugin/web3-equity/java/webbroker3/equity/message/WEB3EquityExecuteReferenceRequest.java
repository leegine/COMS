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
filename	WEB3EquityExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ�N�G�X�g(WEB3EquityExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �����F (���u) �V�K�쐬
Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1195
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������Ɖ�N�G�X�g�j�B<BR>
 * <BR>
 * ���������������Ɖ�v���@@���N�G�X�g�f�[�^�N���X<BR>
 * <BR>
 * ���������F�u�������Ɖ�v<BR>
 * ���������F�u��������ꗗ�v�̗���ʂŎg�p����B
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceRequest extends WEB3GenRequest
{

    /**
     * ���O�o�́B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceRequest.class);

    /**
     * (�Ɖ�敪)<BR>
     * 0�F�������Ɖ�<BR>
     * 1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j
     */
    public String referenceType;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
     * (����ԋ敪)<BR>
     * ��ʏ���\�����Ȃǃf�t�H���g�́u�w��Ȃ��v<BR>
     * null:�w�薳��<BR>
     * 0:�����@@1:�ꕔ�����@@2:�S������
     */
    public String execType;

    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�\�[�g�L�[)<BR>
     * ���������\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF�������ށA�����敪�A�s��A����敪�A�l�i�����A���s�����A<BR>
     * �@@�@@�@@�@@�@@���������A�������ԁA�������A��������
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �P�y�[�W���ɕ\�����������s��
     */
    public String pageSize;

    /**
     * PTYPE
     */
    public static final String PTYPE = "equity_exec_reference";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200405121057L;

    /**
     * (���������������Ɖ�N�G�X�g)
     * �R���X�g���N�^<BR>
     * @@roseuid 40638FD400F3
     */
    public WEB3EquityExecuteReferenceRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40638FB70383
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityExecuteReferenceResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P?�P�jthis.�\�[�g�L�[��null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P?�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P?�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�P?�R?�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�P?�R?�Q�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�i�����j�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�s��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E����敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�l�i����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���s����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E��������<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00055<BR>
     * <BR>
     * �Q�j�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@���N�G�X�g�f�[�^�D�Ɖ�敪��null�̒l�ł����<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00081<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���N�G�X�g�f�[�^�D�Ɖ�敪���h�f�t�H���g�i�������<BR>
     * �@@�@@�@@�@@�Ɖ��ʁj�h�A�h��������\�Ȃ��̂̂ݕ\���i����<BR>
     * �@@�@@�@@�@@����ꗗ��ʁj�h�ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00082<BR>
     * <BR>
     * �R�j�@@����ԋ敪�`�F�b�N<BR>
     * �@@����Ԃ�null�łȂ��A���ȉ��̒l�ȊO�̏ꍇ�A��O��<BR>
     * �X���[����B<BR>
     * �@@�@@0:�����@@1:�ꕔ���@@2:�S�����<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_01697<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@this.�v���y�[�W�ԍ����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00089<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00090<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@this.�y�[�W���\���s�����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�y�[�W���\���s����null�v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00091<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00092<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * �U�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h��null�A<BR>
     * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@�E�Q�F���<BR>
     * �@@�@@�@@�E�R�F���É�<BR>
     * �@@�@@�@@�E�U�F����<BR>
     * �@@�@@�@@�E�W�F�D�y<BR>
     * �@@�@@�@@�E�X�FNNM<BR>
     * �@@�@@�@@�E�P�O�FJASDAQ 
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �V�j�@@���������敪�`�F�b�N <BR>
     * �@@this.���������敪��null�A<BR>
     * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     *�@@�u���������敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�E�O�F�w��Ȃ�<BR>
     * �@@�@@�E�P�F�t�w�l<BR>
     * �@@�@@�E�Q�FW�w�l<BR>
     *          class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4064035A00EC
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g�L�[�̃`�F�b�N
        if(this.sortKeys == null)
        {
            // �\�[�g�L�[��null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        int l_intLength = this.sortKeys.length;
        if(l_intLength == 0)
        {
            // �\�[�g�L�[.�v�f����0�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        for (int i = 0; i < l_intLength; i++)
        {
            // �\�[�g�L�[.validate()���s��
            this.sortKeys[i].validate();
            
            if (!sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE) //�i�����j�R�[�h
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE) //����
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.TRADEMARKET) //�s��
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.TRADETYPE) //����敪
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.PRICE_COND) //�l�i����
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.EXECUTE_COND) //���s����
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_COND) //��������
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIME) //��������
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_DATE) //������
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIMELIMIT) //��������
            )
            {
                log.error("BUSINESS_ERROR_00055:'�\�[�g���ڂ��s���ł��B");
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00055,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //�Q�j�Ɖ�敪�`�F�b�N
        if (referenceType == null)
        {
            //�Ɖ�敪��null�̒l�ł���Η�O���X���[����B
            log.error("BUSINESS_ERROR_00081:�Y���f�[�^�Ȃ��B");
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (
            !this.referenceType.equals(
                WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW)
            // not �������Ɖ���
                && !this.referenceType.equals(
                    WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE))
            // not ��������ꗗ���
        {
            //�Ɖ�敪���h�f�t�H���g�i�������Ɖ��ʁj�h�A�h��������\�Ȃ��̂̂ݕ\���i��������ꗗ��ʁj�h�ȊO�̒l�ł���Η�O���X���[����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�R�j����ԋ敪�`�F�b�N
        if (execType != null // ����Ԃ�null�łȂ�
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE)
            // not �����  
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE)
            // not �ꕔ����
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE)
            // not �S������
        )
        {
            //����Ԃ�null�łȂ��A���ȉ��̒l�ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01697,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�S�j�v���y�[�W�ԍ��̃`�F�b�N
        if(this.pageIndex == null)
        {
            // �v���y�[�W�ԍ���null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            if(Integer.parseInt(this.pageIndex) <= 0)
            {
                // �v���y�[�W�ԍ���0�ȉ��̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        } catch(NumberFormatException e)
        {
            // �v���y�[�W�ԍ��������ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�T�j�@@�y�[�W���\���s���̃`�F�b�N
        if(this.pageSize == null)
        {
            // �y�[�W���\���s����null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            if(Integer.parseInt(this.pageSize) <= 0)
            {
                // �y�[�W���\���s����0�ȉ��̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        } catch(NumberFormatException e)
        {
            // �y�[�W���\���s���������ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�U�j�@@�s��R�[�h�`�F�b�N
        if(this.marketCode != null)
        {
            if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
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
        }

         //�V�j�@@���������敪�`�F�b�N
         //  this.���������敪��null�A�����L�̒l�ȊO�̏ꍇ
        //   �u���������敪������`�̒l�v�̗�O���X���[����B
        if (this.orderCondType != null)
        {
            if (!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                log.debug("���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
