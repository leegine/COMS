head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionUpdateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g(WEB3AdminPvInfoConditionUpdateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ������(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g)<BR>
 * �Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionUpdateRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionUpdateRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionUpdate";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����eID)<BR>
     */
    public String displayContentsId;
    
    /**
     * (�L��/�����敪)<BR>
     * �L��/�����敪<BR>
     * <BR>
     * 0�F�@@�L��<BR>
     * 1�F�@@����<BR>
     */
    public String effectiveFlag;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�����eID�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�����eID == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����eID��null�v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_01040<BR>
     * <BR>
     * �Q�j�L��/�����敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�L��/�����敪 == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�L��/�����敪��null�v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_01041<BR>
     * @@roseuid 415BEF8F0133
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final  String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�����eID�̃`�F�b�N
        //�P�|�P�jthis.�\�����eID == null�̏ꍇ�́A�u�\�����eID��null�v�̗�O���X���[����B
        if(this.displayContentsId == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01040.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01040,
                getClass().getName() + "." + STR_METHOD_NAME); 
        }

        //�Q�j�L��/�����敪�`�F�b�N     
        //�Q�|�P�jthis.�L��/�����敪 == null�̏ꍇ�́A�u�L��/�����敪��null�v�̗�O���X���[����B
        if(this.effectiveFlag == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01041.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01041,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327BE030D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionUpdateResponse(this);
    }
}
@
