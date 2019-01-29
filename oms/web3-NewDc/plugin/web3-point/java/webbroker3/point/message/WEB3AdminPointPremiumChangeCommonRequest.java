head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�������ʃ��N�G�X�g(WEB3AdminPointPremiumChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�i�������ʃ��N�G�X�g)<BR>
 * �i�i�������ʃ��N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeCommonRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumChangeCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumChangeCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290083L;
    
    /**
     * (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     */
    public String premiumNo;
    
    /**
     * (������i�i��)<BR>
     * ������̌i�i��<BR>
     */
    public String afterPremiumName;
    
    /**
     * (������K�v�|�C���g)<BR>
     * ������̕K�v�|�C���g<BR>
     */
    public String afterRequiredPoint;
    
    /**
     * (������񋟊J�n����)<BR>
     * ������̒񋟊J�n����<BR>
     */
    public Date afterStartDate;
    
    /**
     * (������񋟏I������)<BR>
     * ������̒񋟏I������<BR>
     */
    public Date afterEndDate;
    
    /**
     * @@roseuid 41D1254A000F
     */
    public WEB3AdminPointPremiumChangeCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�i�i�ԍ�<BR>
     * <BR>
     *    this.�i�i�ԍ� = null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01727<BR>
     * @@roseuid 419346ED0146
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�i�i�ԍ�
        if (this.premiumNo == null || "".equals(this.premiumNo.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727,
                getClass().getName() + STR_METHOD_NAME,
                "this.�i�i�ԍ� = null�̏ꍇ�A��O���X���[����");
                
            log.debug("�|�C���g�V�X�e��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254A002E
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
