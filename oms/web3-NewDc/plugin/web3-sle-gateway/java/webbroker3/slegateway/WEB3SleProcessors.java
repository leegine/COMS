head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleProcessors.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 *Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 *File Name        : WEB3SleProcessors�N���X
 *Author Name      : Daiwa Institute of Research
 *Revision History : 2006/6/4 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * ���̃N���X�͐������v���Z�b�T���擾���邽�߂̃��\�b�h���`���Ă���
 * 
 * @@author  ��(FLJ)
 * @@version 1.0
 */
public interface WEB3SleProcessors
{
    /**
     * ���ׂẴN�G���ɗ��p�ł���f�t�H���g�N�G���v���Z�b�T���擾���܂��B
     * @@throws DataNetworkException
     * @@throws DataFindException
     */
    public QueryProcessor getDefaultProcessor() throws DataNetworkException,DataFindException;
    
}
@
