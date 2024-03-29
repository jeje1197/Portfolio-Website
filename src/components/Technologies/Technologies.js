import React from 'react';
import { DiFirebase, DiReact, DiZend } from 'react-icons/di';
import { Section, SectionDivider, SectionText, SectionTitle } from '../../styles/GlobalComponents';
import { List, ListContainer, ListItem, ListParagraph, ListTitle } from './TechnologiesStyles';

const Technologies = () =>  (
  <Section id="tech">
    <SectionDivider />
    <SectionTitle>Technologies</SectionTitle>
    <SectionText>
      I've worked with a range of technologies in the web development world
      From Front-End To Back-End
    </SectionText>
    <List>
      <ListItem>
        <DiReact size="3rem/" />
          <ListContainer>
            <ListTitle>Front-End</ListTitle>
            <ListParagraph>
              Experience with React, MaterialUI, Bootstrap, TailwindCSS, JavaScript
            </ListParagraph>
          </ListContainer>
      </ListItem>
      <ListItem>
        <DiFirebase size="3rem/" />
          <ListContainer>
            <ListTitle>Back-End</ListTitle>
            <ListParagraph>
              Experience with Node, Java, Python, SQL
            </ListParagraph>
          </ListContainer>
      </ListItem>
      <ListItem>
        <DiZend size="3rem/" />
          <ListContainer>
            <ListTitle>UI/UX</ListTitle>
            <ListParagraph>
              Experience with Adobe, Drawio, asdf
            </ListParagraph>
          </ListContainer>
      </ListItem>
    </List>
  </Section>
);

export default Technologies;
