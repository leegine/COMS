head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqNumberService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X(WEB3FaqNumberService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (�⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X)<BR>
 * �⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3FaqNumberService extends Service 
{
    
    /**
     * (get�V�K�⍇���R�[�h)<BR>
     * �⍇���R�[�h���̔Ԃ���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return String
     * @@roseuid 41ABF2F0017C
     */
    public String getNewFaqNumber(String l_strInstitutionCode) throws WEB3BaseException;
}
@
