head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingContentConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݒ���e�m�F���N�G�X�g(WEB3SuccSettingContentConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�ݒ���e�m�F���N�G�X�g)<BR>
 * �ݒ���e�m�F���N�G�X�g�N���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccSettingContentConfirmRequest extends WEB3GenRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccSettingContentConfirmRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingContentConfirm";
    
    /**
     * (�i�e�����j����ID)<BR>
     * �i�e�����j����ID�B<BR>
     */
    public String parentOrderId;
    
    /**
     * (���i�敪)<BR>
     * �e�����̏��i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String commodityType;
    
    /**
     * @@roseuid 4348960401E4
     */
    public WEB3SuccSettingContentConfirmRequest() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�i�e�����j����ID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�i�e�����j����ID�������͂̏ꍇ�A<BR>
     * �@@�@@�u����ID�������́v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02258<BR>
     * <BR>
     * �Q�j�@@���i�敪�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.���i�敪�������͂̏ꍇ�A<BR>
     * �@@�@@�u���i�敪�������́v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02182<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.���i�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�u����`�̏��i�敪�����݁v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_01068<BR>
     * �@@�@@�@@"��������"<BR>
     * �@@�@@�@@"�M�p���"<BR>
     * �@@�@@�@@"�敨"<BR>
     * �@@�@@�@@"�I�v�V����"<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43262F3300A6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�i�e�����j����ID�̃`�F�b�N
        // �@@�P�|�P�j�@@this.�i�e�����j����ID�������͂̏ꍇ�A<BR>
        // �@@�@@�u����ID�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.parentOrderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02258,
                getClass().getName() + STR_METHOD_NAME,
                "�i�e�����j����ID�������͂ł��B");
        }

        // �Q�j�@@���i�敪�̃`�F�b�N
        // �@@�Q�|�P�j�@@this.���i�敪�������͂̏ꍇ�A
        // �@@�@@�u���i�敪�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.commodityType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                getClass().getName() + STR_METHOD_NAME,
                "���i�敪�������͂ł��B");
        }

        // �@@�Q�|�Q�j�@@this.���i�敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�u����`�̏��i�敪�����݁v�̗�O���X���[����B
        if (!WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            && !WEB3CommodityDivDef.MARGIN.equals(this.commodityType)
            && !WEB3CommodityDivDef.FUTURE.equals(this.commodityType)
            && !WEB3CommodityDivDef.OPTION.equals(this.commodityType))
        
        {
            String l_strMessage = "���i�敪�u" + this.commodityType + "�v�����݂��Ȃ��R�[�h�l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccSettingContentConfirmResponse(this);
    }
}
@
