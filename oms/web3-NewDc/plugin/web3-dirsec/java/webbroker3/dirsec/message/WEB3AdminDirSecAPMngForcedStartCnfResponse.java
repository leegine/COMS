head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCnfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE���菈�������N���m�F���X�|���X(WEB3AdminDirSecAPMngForcedStartCnfResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���菈�������N���m�F���X�|���X)<BR>
 * �Ǘ��ҁE���菈�������N���m�F���X�|���X�N���X�B<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCnfResponse extends WEB3GenResponse
{

    /**
	 * serialVersionUID<BR>
	 */
	private static final long serialVersionUID = 200807211640L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_forced_start_cnf";

	/**
     * @@roseuid 488437FE00B4
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@