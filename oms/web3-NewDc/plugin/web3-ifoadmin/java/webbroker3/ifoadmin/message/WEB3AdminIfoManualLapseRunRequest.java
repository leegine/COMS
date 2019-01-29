head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualLapseRunRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g(WEB3AdminIfoManualLapseRunRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g)<BR>
 * �Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g�N���X<BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseRunRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseRunRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseRun";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     *(�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password = null;

    /**
     * (�����Ώے�������)
     */
    public WEB3AdminIfoLapseTargetOrderCondition ifoLapseTargetOrderCondition;
    
    /**
     * @@roseuid 447AB8F401D4
     */
    public WEB3AdminIfoManualLapseRunRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�����Ώے��������̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A<BR>
     * �@@�@@�u�����Ώے��������������́v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02420<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����Ώے��������̃`�F�b�N 
        //�@@�P�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A 
        //�@@�@@�u�����Ώے��������������́v�̗�O���X���[����B 
        if (this.ifoLapseTargetOrderCondition == null) 
        {
            log.debug("�����Ώے��������������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����Ώے��������������͂ł��B");
        }
        
        //�@@�P�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B
        this.ifoLapseTargetOrderCondition.validate();
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁE�����蓮���������N�����X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIfoManualLapseRunResponse(this);
    }
}
@
