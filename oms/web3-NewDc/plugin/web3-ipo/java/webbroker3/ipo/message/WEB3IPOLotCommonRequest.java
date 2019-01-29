head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�������ʃ��N�G�X�g(WEB3IPOLotCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO���I�������ʃ��N�G�X�g)<BR>
 *  �Ǘ���IPO���I�������ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �A����
 * @@version 1.0
 */
public class WEB3IPOLotCommonRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOLotCommonRequest.class);

    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_lotCommon";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200512192100L;
    
    /**
     * (��ʋ敪)<BR>
     * �J�ڌ��̉�ʂ������敪�B<BR>
     * <BR>
     * �P�F�o�^<BR>
     * �Q�F�Q��
     */
    public String displayDiv;
    
    /**
     * (����ID)<BR>
     * IPO����ID�B
     */
    public String id;
    
    /**
     * @@roseuid 4112E68202AA
     */
    public WEB3IPOLotCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@��ʋ敪�`�F�b�N <BR>
     * �@@�P�|�P�jthis.��ʋ敪 == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u��ʋ敪��null�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02325<BR>
     * <BR>
     * �Q�j�@@����ID�`�F�b�N <BR>
     * �@@�Q�|�P�jthis.����ID == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02229<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�|�P�jthis.��ʋ敪 == null�̏ꍇ�A
        //�@@�@@�@@�@@�u��ʋ敪��null�v�̗�O���X���[����B
        if (this.displayDiv == null) 
        {
            log.debug("��ʋ敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02325,
                getClass().getName() + "." + STR_METHOD_NAME,
                "��ʋ敪�����w��ł��B");
        }
        
        //�Q�|�P�jthis.����ID == null�̏ꍇ�A 
        //�@@�@@�@@�@@����ID��null�v�̗�O���X���[����B
        if (this.id == null) 
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A02FD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOLotCommonResponse(this);
    }
}
@
