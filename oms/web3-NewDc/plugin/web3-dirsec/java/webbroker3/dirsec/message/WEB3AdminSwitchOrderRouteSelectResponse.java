head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ�����֑ؑI�����X�|���X(WEB3AdminSwitchOrderSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ��ҁE������֑ؑI�����X�|���X�N���X<BR>
 */
public class WEB3AdminSwitchOrderRouteSelectResponse extends WEB3GenResponse
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
	 * ��������ꗗ<BR>
	 */
	public WEB3AdminOrderRouteSwitchingInfo[] infoList;

    /**
     * �Ǘ��Ҕ�����֑ؑI�����X�|���X�R���X�g���N�^<BR>
     * @@roseuid 42E46E2B005D
     */
    public WEB3AdminSwitchOrderRouteSelectResponse() 
    {
     
    }

	/**
	 *
	 * @@param l_request WEB3GenRequest<BR>
	 */
	public WEB3AdminSwitchOrderRouteSelectResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}

}
@
