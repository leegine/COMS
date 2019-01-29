head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCsvUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright                : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name                : �A���Ǘ�CSV���ʏ����N���X(WEB3InformCsvUtility)
Author Name           : Daiwa Institute of Research
Revesion History     : 2005/02/03 ���Ō�(���u) �쐬
*/
package webbroker3.inform.util;

/**
 * 
 * �A���Ǘ�CSV���ʏ����N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3InformCsvUtility
{
    private static char csvValueSeperator = '"';
    
    /**
     *�R���X�g���N�^ 
     */
    private WEB3InformCsvUtility()
    {
        super();
    }

    /**
     * getValueForCSVLine
     *
     *CSV�s�p�̒l���擾.
     *�������[��:
     *�@@�@@1)���ڒl�̓_�u���N�I�[�g�i�h�j�ň͂ށB
     * �@@�@@�@@��:  �����R�[�h >> "�����R�[�h"
     *  �@@�@@�@@�@@�@@12345  >> "12345"
     *�@@�@@2)���ڒl�� �_�u���N�I�[�g(")���܂ޏꍇ.
     *       �_�u���N�I�[�g(")�̒��O�ɂ�����_�u���N�I�[�g(")��ǉ����܂��B
     * �@@�@@�@@��:�@@123"455 >> 123""455
     * �@@�@@�@@�@@�@@�@@����"�R�[�h"�@@>>�@@����""�R�[�h""
     */
    private static String getValueForCSVLine(String l_strValue)
    {
        if (l_strValue == null)
        {
            return null;
        }
        
        int          end;
        int          start = 0;
        StringBuffer buf;

        end = l_strValue.indexOf(csvValueSeperator);

        if (end == -1) 
        {
            return new String(csvValueSeperator + l_strValue + csvValueSeperator);
        }

        buf = new StringBuffer();
        
        while (true) 
        {
            if (end == -1) 
            {
                buf.append(l_strValue.substring(start));
                break;
            }
            
            buf.append(l_strValue.substring(start, end));
            buf.append(csvValueSeperator);
            buf.append(csvValueSeperator);
            start = end + 1;
            
            if (start >= l_strValue.length()) break;

            end = l_strValue.indexOf(csvValueSeperator, start);
        }

        return new String(csvValueSeperator + buf.toString() + csvValueSeperator);
        
    }
}
@
