head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ�����֑ؑI�����N�G�X�g(WEB3AdminSwitchOrderRouteSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ��ҁE������֑ؑI�����N�G�X�g�N���X<BR>
 */
public class WEB3AdminSwitchOrderRouteSelectRequest extends WEB3GenRequest
{
	
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_switch_order_route_select";

	/**
	 * serialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200502011606L;
	     
	/**
	 * �،���ЃR�[�h<BR>
	 */
	public String institutionCode;
    
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteSelectRequest.class);


    /**
     * �Ǘ��Ҕ�����֑ؑI�����N�G�X�g�R���X�g���N�^<BR>
     * @@roseuid 42E46E2B005D
     */
    public WEB3AdminSwitchOrderRouteSelectRequest() 
    {
     
    }

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
	 * <BR>
	 * �P�j�،���ЃR�[�h�`�F�b�N<BR>
	 * �@@�P�|�P�jthis.�،���ЃR�[�h == null�̏ꍇ�A<BR>
	 * �@@�@@�@@�@@�@@�uthis.�،���ЃR�[�h��null�v�̗�O���X���[����B<BR>
	 * <BR>
	 *            class : WEB3BusinessLayerException<BR>
	 *            tag : BUSINESS_ERROR_00079<BR>
	 * <BR>
	*/
	public void validate() throws WEB3BusinessLayerException
	{
		
		final String STR_METHOD_NAME = "validate()";

		log.entering(STR_METHOD_NAME);

		// 1-1 �،���ЃR�[�h�@@null�`�F�b�N
		if (this.institutionCode == null)
		{

			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00827,
				this.getClass().getName() + "." + STR_METHOD_NAME);

		}
		log.exiting(STR_METHOD_NAME);
	}

	/** (non-Javadoc)
	 * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminSwitchOrderRouteSelectResponse(this);
	}

}
@
