head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopUpdInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g(WEB3AdminToTradeStopUpdInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���@@�F(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g�N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToTradeStopUpdInputRequest extends WEB3AdminToCommonRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopUpdInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_upd_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F�@@���i<BR>
     * 2�F�@@�s��<BR>
     * 3�F�@@����<BR>
     */
    public String tradeStopDiv;
    
    /**
     * (���ꎷ�s�����戵��~ID)<BR>
     * ���ꎷ�s�����戵��~ID<BR>
     * <BR>
     * �������敪 == "����"�̏ꍇ�Z�b�g�B<BR>
     */
    public String triggerTradeStopId = null;
    
    /**
     * @@roseuid 4430D3B9038A
     */
    public WEB3AdminToTradeStopUpdInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����敪�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�����敪 == null�̏ꍇ�A<BR>
     * �@@�@@�u�����敪�������́v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01249<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�����敪�Ɉȉ��̒l�ȊO���ݒ肳��Ă���<BR>
     * �@@�@@�ꍇ�A�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���i"<BR>
     * �@@�@@�@@�E"�s��"<BR>
     * �@@�@@�@@�E"����"<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01250<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BCCE02A0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�jsuper.validate()���R�[������B
        super.validate();
        
        // �Q�j�����敪�`�F�b�N
        // �@@�Q�|�P�jthis.�����敪 == null�̏ꍇ�A
        // �@@�@@     �u�����敪�������́v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01249
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopDiv))
        {
            log.debug("�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");            
        }
        
        // �@@�Q�|�Q�jthis.�����敪�Ɉȉ��̒l�ȊO���ݒ肳��Ă���
        // �@@�@@       �ꍇ�A�u�����敪������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@     �E"���i"
        // �@@�@@�@@     �E"�s��"
        // �@@�@@�@@     �E"����"
        //            class : WEB3BusinessLayerException
        //            tag : BUSINESS_ERROR_01250
        if (!(WEB3TargetTypeDef.COMMODITY.equals(this.tradeStopDiv)
            || WEB3TargetTypeDef.MARKET.equals(this.tradeStopDiv)
            || WEB3TargetTypeDef.PRODUCT.equals(this.tradeStopDiv)))
        {
            log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");               
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopUpdInputResponse(this);
    }
}
@
