head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ�����ؑ֊m�F���N�G�X�g(WEB3AdminSwitchOrderRouteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * �Ǘ��ҁE������ؑ֊m�F���N�G�X�g�N���X<BR>
 */
public class WEB3AdminSwitchOrderRouteConfirmRequest extends WEB3GenRequest
{

	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_switch_order_route_confirm";

	/**
	 * serialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200502011606L;
    
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteConfirmRequest.class);
    
	/**
	 * �،���ЃR�[�h<BR>
	 */
	public String institutionCode;

	/**
	 * ��������<BR>
	 */
    public WEB3AdminOrderRouteSwitchingInfo infoUnit;
 
    
    /**
     * @@roseuid 42E4849402DE
     */
    public WEB3AdminSwitchOrderRouteConfirmRequest() 
    {
     
    }

    /**
     * ���N���X�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@��������`�F�b�N<BR>
     * �@@this.��������.validate()���R�[������B<BR>
     * @@roseuid 42D2123E0246
     */
    public void validate() throws WEB3BaseException 
    {
     
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
     
		// �،���ЃR�[�h�`�F�b�N
		// �G���[�̏ꍇ�́A��O��throw����B
		if (this.institutionCode == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00827,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        else 
        {
            // ��������N���X��validate�`�F�b�N
            infoUnit.validate();
        }
		log.exiting(STR_METHOD_NAME);
    }
    
	/** (non-Javadoc)
	 * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminSwitchOrderRouteConfirmResponse(this);
	}

   
}
@
