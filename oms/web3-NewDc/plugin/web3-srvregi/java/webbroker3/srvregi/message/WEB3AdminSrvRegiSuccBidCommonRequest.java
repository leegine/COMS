head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSuccBidCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���ʃ��N�G�X�g(WEB3AdminSrvRegiSuccBidCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���ʃ��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSuccBidCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiSuccBidCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_succBidCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151452L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (���I���ID)<BR>
     * �ʔ�<BR>
     */
    public String lotteryId;
    
    /**
     * (�ō����D�z)
     */
    public String maxSuccBidding;
    
    /**
     * (�Œᗎ�D�z)
     */
    public String minSuccBidding;
    
    /**
     * (���d���ϊz)
     */
    public String weightedAverage;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F4EEA80279
     */
    public WEB3AdminSrvRegiSuccBidCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�T�[�r�X�敪�̌���!=2���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) ���I���ID�̃`�F�b�N<BR>
     * �@@this.���I���ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00957<BR>
     * <BR>
     * 3) �ō����D�z�̃`�F�b�N<BR>
     *  3-1) this.�ō����D�z!=null�ł���Athis.�ō����D�z�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00958<BR>
     *  3-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00959<BR>
     * <BR>
     * 4) �Œᗎ�D�z�̃`�F�b�N<BR>
     *  4-1) this.�Œᗎ�D�z!=null�ł���Athis.�Œᗎ�D�z�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00960<BR>
     *  4-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00961<BR>
     * <BR>
     * 5) ���d���ϊz�̃`�F�b�N<BR>
     *  5-1) this.���d���ϊz!=null�ł���Athis.���d���ϊz�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00962<BR>
     *  5-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00974<BR>
     * <BR>
     * 6) �ō����D�z�E�Œᗎ�D�z�̃`�F�b�N<BR>
     * �@@this.�ō����D�z��this.�Œᗎ�D�z�̗���!=null�ł���A����<BR>
     * �@@this.�ō����D�z��this.�Œᗎ�D�z�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00975<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4EEC003C1
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�敪�̃`�F�b�N
        //1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //1-2) this.�T�[�r�X�敪�̌���!=2���̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }

        // 2) ���I���ID�̃`�F�b�N
        //this.���I���ID==null�̏ꍇ�A��O���X���[����B
        if (this.lotteryId == null || lotteryId.trim().length() == 0)
        {
            log.debug("2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00957,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //Bug No:1434
        //3) �ō����D�z�̃`�F�b�N
        //3-1) this.�ō����D�z!=null�ł���Athis.�ō����D�z�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B
        if (this.maxSuccBidding != null && !WEB3StringTypeUtility.isNumber(this.maxSuccBidding))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00958,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����B
        if (this.maxSuccBidding != null && this.maxSuccBidding.length() > 9)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00959,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) �Œᗎ�D�z�̃`�F�b�N
        //4-1) this.�Œᗎ�D�z!=null�ł���Athis.�Œᗎ�D�z�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B
        if (this.minSuccBidding != null && !WEB3StringTypeUtility.isNumber(this.minSuccBidding))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00960,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //4-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����B
        if (this.minSuccBidding != null && this.minSuccBidding.length() > 9)
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00961,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) ���d���ϊz�̃`�F�b�N
        //5-1) this.���d���ϊz!=null�ł���Athis.���d���ϊz�ɐ��l�ȊO���Z�b�g����Ă����ꍇ�A��O���X���[����B
        if (this.weightedAverage != null && !WEB3StringTypeUtility.isNumber(this.weightedAverage))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00962,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //5-2) this�ō����D�z�̌�����9���̏ꍇ,��O���X���[����
        if (this.weightedAverage != null && this.weightedAverage.length() > 9)
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00974,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) �ō����D�z�E�Œᗎ�D�z�̃`�F�b�N
        //this.�ō����D�z��this.�Œᗎ�D�z�̗���!=null�ł���A����
        //this.�ō����D�z��this.�Œᗎ�D�z�̏ꍇ�A��O���X���[����B
        if ((this.maxSuccBidding != null && maxSuccBidding.trim().length() != 0) &&
            (this.minSuccBidding != null && minSuccBidding.trim().length() != 0) &&
            Integer.parseInt(this.maxSuccBidding) < Integer.parseInt(this.minSuccBidding))
        {
            log.debug("6)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00975,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F466D031C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
