head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseStatusResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���X�|���X(WEB3AdminEquityManualLapseStatusResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���X�|���X)<BR>
 * �Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���X�|���X�N���X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseStatusResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseStatus";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * (�����X�e�[�^�X)<BR>
     * �����X�e�[�^�X<BR>
     * <BR>
     * 1�F�@@������<BR>
     * 5�F�@@������<BR>
     * 9�F�@@�G���[<BR>
     */
    public String lapseStatus;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���
     */
    public Date currentTime;
    
    /**
     * @@roseuid 447AB8F403B9
     */
    public WEB3AdminEquityManualLapseStatusResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminEquityManualLapseStatusResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
