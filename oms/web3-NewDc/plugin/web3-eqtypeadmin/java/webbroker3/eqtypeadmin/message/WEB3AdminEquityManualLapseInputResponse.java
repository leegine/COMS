head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�������̓��X�|���X(WEB3AdminEquityManualLapseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����蓮�������̓��X�|���X)<BR>
 * �Ǘ��ҁE�����蓮�������̓��X�|���X�N���X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ
     */
    public String[] marketList;
    
    /**
     * @@roseuid 447AB8F3032C
     */
    public WEB3AdminEquityManualLapseInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminEquityManualLapseInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
