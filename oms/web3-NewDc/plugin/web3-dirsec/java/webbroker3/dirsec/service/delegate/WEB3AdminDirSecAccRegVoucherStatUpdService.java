head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X(WEB3AdminDirSecAccRegVoucherStatUpdService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
Revision History    : 2007/06/18 �đo�g (���u) �d�l�ύX ���f��No.101
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X)<BR>
 * �Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AdminDirSecAccRegVoucherStatUpdService extends WEB3BusinessService
{
    /**
     * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������J�n����B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4653FC9A0088
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
