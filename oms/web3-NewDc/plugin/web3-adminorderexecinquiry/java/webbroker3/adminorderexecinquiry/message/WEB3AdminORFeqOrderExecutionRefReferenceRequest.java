head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�O�������������Ɖ�N�G�X�g(WEB3AdminORFeqOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminFeqSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�O�������������Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҁE�O�������������Ɖ�N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefReferenceRequest extends WEB3AdminOROrderExecutionRefCommonRequest
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFeqOrderExecutionRefReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefReference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode = null;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode = null;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     */
    public String taxType = null;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode = null;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode = null;
    
    /**
     * @@roseuid 42D1C8EA008C
     */
    public WEB3AdminORFeqOrderExecutionRefReferenceRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁE�O�������������Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFeqOrderExecutionRefReferenceResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * <BR>
     * �Q�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�Q�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@ �@@tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�E�u����ID�v<BR>
     * �@@�@@�@@�E�u���X�R�[�h�v<BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�E�u�s��R�[�h�v<BR>
     * �@@�@@�@@�E�u�����敪�v<BR>
     * �@@�@@�@@�E�u����敪�v<BR>
     * �@@�@@�@@�E�u���ϋ敪�v<BR>
     * �@@�@@�@@�E�u�������ԁv<BR>
     * �@@�@@�@@�E�u�������v<BR>
     * �@@�@@�@@�E�u���s�����v<BR>
     * �@@�@@�@@�E�u���������v<BR>
     * �@@�@@�@@�E�u���������v<BR>
     * �@@�@@�@@�E�u��n���v<BR>
     * �@@�@@�@@�E�u�^�p�R�[�h�v<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A65D0A005F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        

        //�P�j�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
        
         //2�j�\�[�g�L�[�`�F�b�N
        //�@@2�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        int l_intSorkKeyCount = 0;
        if (super.sortKeys != null)
        {
            l_intSorkKeyCount = super.sortKeys.length;
        }
        for (int i = 0; i < l_intSorkKeyCount; i++)
        {
            //�@@�@@2�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��
            //�@@�@@�@@�ݒ肳��Ă�����A
            //�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
            String l_strKeyItem = super.sortKeys[i].keyItem;
            if (!WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem)    //����ID
                && !WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem)    //���X�R�[�h
                && !WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem)    //�ڋq�R�[�h
                && !WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem)    //�����R�[�h
                && !WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem)    //�s��R�[�h
                && !WEB3AdminFeqSortKeyDef.TAX_TYPE.equals(l_strKeyItem)    //�����敪
                && !WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem)    //����敪
                && !WEB3AdminFeqSortKeyDef.SETTLE_DIV.equals(l_strKeyItem)    //���ϋ敪
                && !WEB3AdminFeqSortKeyDef.ORDER_START_DATE.equals(l_strKeyItem)    //��������
                && !WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem)    //������
                && !WEB3AdminFeqSortKeyDef.EXECCOND_TYPE.equals(l_strKeyItem)    //���s����
                && !WEB3AdminFeqSortKeyDef.EXPIRATION_DATE_TYPE.equals(l_strKeyItem)    //��������
                && !WEB3AdminFeqSortKeyDef.ORDER_COND_TYPE.equals(l_strKeyItem)    //��������
                && !WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem)    //��n��
                && !WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))    //�^�p�R�[�h
            {
                String l_strMessage = "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B�u" + l_strKeyItem + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
     
        log.exiting(STR_METHOD_NAME);
    }
}
@
