head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�������C�����X�|���X(WEB3AdminEquityManualLapseMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�Ǘ��ҁE�����蓮�������C�����X�|���X)<BR>
 * �Ǘ��ҁE�����蓮�������C�����X�|���X�N���X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseMainResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseMain";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * @@roseuid 447AB8F40119
     */
    public WEB3AdminEquityManualLapseMainResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminEquityManualLapseMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}
@
