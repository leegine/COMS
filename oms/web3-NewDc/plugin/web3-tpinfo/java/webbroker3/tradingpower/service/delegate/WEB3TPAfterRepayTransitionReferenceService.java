head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ԍό�]�͏���ʃT�[�r�X(WEB3TPAfterRepayTransitionReferenceService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) �V�K�쐬
 */
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�ԍό�]�͏���ʃT�[�r�X)
 */
public interface WEB3TPAfterRepayTransitionReferenceService
        extends WEB3BusinessService
{
    /**
     * (execute)
     * 
     * ���V�[�P���X�}�u�i���Y�]�͏���ʕ\���T�[�r�X�jexecute�v�Q��
     * 
     * @@param ���N�G�X�g
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException;
}@
