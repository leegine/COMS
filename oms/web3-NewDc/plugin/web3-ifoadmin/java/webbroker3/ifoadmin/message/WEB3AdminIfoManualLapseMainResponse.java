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
filename	WEB3AdminIfoManualLapseMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�������C�����X�|���X(WEB3AdminIfoManualLapseMainResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�Ǘ��ҁE�敨OP�蓮�������C�����X�|���X)<BR>
 * �Ǘ��ҁE�敨OP�蓮�������C�����X�|���X�N���X<BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseMainResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseMain";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * @@roseuid 447AB8F40119
     */
    public WEB3AdminIfoManualLapseMainResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminIfoManualLapseMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}
@
