head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLAccountOpenUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J��UnitService(WEB3AioSLAccountOpenUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �Ӑ� (���u) �V�K�쐬 ���f��No.763
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (SL�����J��UnitService)<BR>
 * SL�����J��UnitService�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public interface WEB3AioSLAccountOpenUnitService extends WEB3BusinessService
{
    /**
     * SL�����J�݃T�[�r�X�������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE071503C9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
