head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^�T�[�r�X(WEB3MutualFixedBuyConditionService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ���z(���u) �V�K�쐬 ���f��608
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���M�莞��z���t���������o�^�T�[�r�X)<BR>
 * ���M�莞��z���t���������o�^�T�[�r�X<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3MutualFixedBuyConditionService extends WEB3BusinessService
{
    /**
     * �����莞��z���t���������o�^�T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851CB720293
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
