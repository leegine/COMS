head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^�T�[�r�X(WEB3AccOpenMailAddressRegistService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �И��� (���u) �V�K�쐬 ���f�� No.166
*/
package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�����J�݃��[���A�h���X�o�^�T�[�r�X)<BR>
 * �����J�݃��[���A�h���X�o�^�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public interface WEB3AccOpenMailAddressRegistService extends WEB3BusinessService
{
    /**
     * �����J�݃��[���A�h���X�o�^���������{����B<BR>
     * @@param l_request - (���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
