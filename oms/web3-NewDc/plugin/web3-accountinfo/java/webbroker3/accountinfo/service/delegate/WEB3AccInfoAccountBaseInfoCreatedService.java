head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ڋq��{���쐬�T�[�r�X(WEB3AccInfoAccountBaseInfoCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (���q�l���ڋq��{���쐬�T�[�r�X)<BR>
 * ���q�l���ڋq��{���쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AccInfoAccountBaseInfoCreatedService extends Service 
{
    /**
     * (create�ڋq��{���)<BR>
     * �ڋq�I�u�W�F�N�g���A�ڋq��{��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo
     * @@roseuid 415D16F4032A
     */
    public WEB3AccInfoAccountBaseInfo createAccountBaseInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
    
    /**
     * (create��~���)<BR>
     * �ڋq�I�u�W�F�N�g���A��~��񃁃b�Z�[�W�f�[�^���쐬����B <BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@return�@@webbroker3.accountinfo.message.WEB3AccInfoStopInfo
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoStopInfo createStopInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException; 
}
@
